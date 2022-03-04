package com.svi.advancedjavatraining;

import com.svi.advancedjavatraining.object.City;
import com.svi.advancedjavatraining.object.CityInfo;
import com.svi.advancedjavatraining.object.PopulationData;
import com.svi.advancedjavatraining.object.Province;
import com.svi.advancedjavatraining.utils.JSONFileReader;
import com.svi.advancedjavatraining.utils.PopulationFileWriter;
import com.svi.advancedjavatraining.utils.WebLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		// START WORK AREA
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// Create a webloader instance
		WebLoader webLoader = new WebLoader();
		// Submit a callable to load province data
		Future<List<Province>> provinceFuture = executorService.submit(new Callable<List<Province>>()
		{
			@Override
			public List<Province> call() throws Exception
			{
				return webLoader.getProvinces();
			}
		} );

		// Submit a callable to load city data
		Future<List<City>> cityFuture = executorService.submit(new Callable<List<City>>()
		{
			@Override
			public List<City> call() throws Exception
			{
				return webLoader.getCities();
			}
		} );

		try
		{
			// Wait and get province data
			List<Province> provinces = provinceFuture.get();

			// If province data could not be loaded
			if( provinces.isEmpty() )
			{
				System.out.println("Could not load province data");
				return;
			}

			// Map to keep Province keyed by province key
			Map<String, Province> provinceByKeyMap = new HashMap<>();
			// Populate above map, ideally only 1 province should be present for a key
			for(Province province : provinces)
			{
				provinceByKeyMap.put(province.getKey(), province);
			}

			// Wait and get city data
			List<City> cities = cityFuture.get();

			// If city data could not be loaded
			if(cities.isEmpty())
			{
				System.out.println("Could not load city data");
				return;
			}

			List<PopulationData> populationDataList = new ArrayList<>();
			// Futures list which retrieve population data
			List<Future<PopulationData>> populationDataTasks = new ArrayList<>();

			for(City city : cities)
			{
				// Get province for current city
				Province province = provinceByKeyMap.get( city.getProvince());

				// If province is present
				if(province != null)
				{
					// Create callable to read and return data for a city
					Callable<PopulationData> readFileTask = new Callable<>()
					{
						@Override
						public PopulationData call() throws Exception
						{
							JSONFileReader jsonFileReader = new JSONFileReader(province.getName(), city.getName());
							try
							{
								CityInfo cityInfo = jsonFileReader.getData();
								float population = 0f;

								// If city info is not present(no file), population is defaulted to 0
								if(cityInfo != null)
									population = cityInfo.getPopulation();

								// Create a population object with retrieved data and return it
								return new PopulationData(province.getName(), city.getName(), population);
							}
							catch(IOException e)
							{
								System.out.println("Failed to load information for city " + city.getName());
							}
							// No population data found
							return null;
						}
					};

					// Add a future task to read and parse relevant city json file
					populationDataTasks.add(executorService.submit(readFileTask));
				}
			}

			// Signal executor service to shut down. It'll shut down after pending tasks are completed
			executorService.shutdown();

			for(Future<PopulationData> populationDataTask : populationDataTasks)
			{
				// Wait for task to complete
				PopulationData populationData = populationDataTask.get();
				// If future returns valid data, add it to population list
				if(populationData != null)
				{
					populationDataList.add(populationData);
				}
			}

			// Create a filewriter to write to excel file
			PopulationFileWriter populationFileWriter = new PopulationFileWriter();

			// Displaying population data in a table
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf( "|%20s | %25s | %20s |%n", "Province", "City", "Population" );
			System.out.println("--------------------------------------------------------------------------");

			// For each data point
			for(PopulationData populationData : populationDataList)
			{
				// Add to file writer
				populationFileWriter.addPopulationRecord(populationData);
				// Print data
				System.out.printf("|%20s | %25s | %20d |%n", populationData.getProvince(), populationData.getCity(), (int)populationData.getPopulation());
			}
			System.out.println("--------------------------------------------------------------------------");
			// Write data to file
			populationFileWriter.writeToFile();

		}
		catch(InterruptedException | ExecutionException | IOException e)
		{
			e.printStackTrace();
		}
		// END WORK AREA

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		long millis = TimeUnit.NANOSECONDS.toMillis(totalTime);

		long seconds = TimeUnit.NANOSECONDS.toSeconds(totalTime);

		long minutes = TimeUnit.NANOSECONDS.toMinutes(totalTime);

		long hours = TimeUnit.NANOSECONDS.toHours(totalTime);

		String time = String.format("%d:%02d:%02d.%03d", hours, minutes, seconds, millis);
		System.out.println(time);

	}

}
