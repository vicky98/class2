//--------------------------------------------
// NAME: Victoria Admasu
// CLASS: XIa
// NUMBER: 6
// PROBLEM: #3
// FILE NAME: A_06_Victoria_Admasu.c
// FILE PURPOSE:
// implementation of StarCraft III
//---------------------------------------------

#include <pthread.h>
#include <stdio.h>
#include <time.h>

int map_minerals = 5000;
int owned_minerals = 0;
int collected_minerals_overall = 0;
int soldiers = 0;
int workers_count = 0;

pthread_t command_centers[200];  // array of command centers

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;  // used for the mutex functions

//--------------------------------------------
// FUNCTION: command_center_activity
// The things that are done by the command centers
// PARAMETERS:
// soldiers - the number of soldiers
// owned_minerals - the minerals, owned by the workers
//----------------------------------------------

void* command_center_activity(void* arg)
{
	pthread_mutex_lock(&mutex);

	if (soldiers < 20)
	{
		if (owned_minerals >= 50)
		{
			printf("You wanna piece of me, boy?\n");
			sleep(1);
			soldiers += 1;
			owned_minerals -= 50;
		} else {
			printf("Not enough minerals\n");
		}
	}
	pthread_mutex_unlock(&mutex);	
}

//--------------------------------------------
// FUNCTION: workers_activity
// The things done by the workers
// PARAMETERS:
// num            - the number of the worker
// map_minerals   - the map's minerals
// owned_minerals - the minerals, owned by the workers
//----------------------------------------------

void* workers_activity(void* arg)
{
	pthread_mutex_lock(&mutex);

	int num = (int) arg;

	printf("SCV %d is mining\n", num);
	map_minerals -= 8;
	owned_minerals += 8;
	collected_minerals_overall += 8;

	printf("SCV %d is transporting minerals\n", num);
	sleep(2);

	printf("SCV %d delivered minerals to Command Center 1\n", num);

	pthread_create(&command_centers[num - 1], NULL, command_center_activity, (void*) (arg));

	pthread_mutex_unlock(&mutex);
}

//--------------------------------------------
// FUNCTION: main
// PARAMETERS:
// workers[5]         		  - array of workers
// workers_count      		  - the number of workers
// soldiers           		  - the number of soldiers
// map_minerals               - the map's minerals
// collected_minerals_overall - the minerals, collected by the workers
// i                  		  - counter
//----------------------------------------------

int main()
{
	pthread_t workers[5];
	workers_count += 5;
	int i = 0;
	
	while (soldiers < 20)
	{
		for (i = 0; i < 5; i++)
			pthread_create(&workers[i], NULL, workers_activity, (void*) (i + 1));
	
		for (i = 0; i < 5; i++)
			pthread_join(workers[i], NULL);
	}
		
	printf("Map minerals: 5000\n");
	printf("Left minerals: %d\n", map_minerals);
	printf("Collected minerals: %d\n", collected_minerals_overall);
}