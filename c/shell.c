//--------------------------------------------
// NAME: Victoria Admasu
// CLASS: XIa
// NUMBER: 6
// PROBLEM: #2
// FILE NAME: A_06_Victoria_Admasu.c
// FILE PURPOSE:
// Implementation of shell
//---------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

char** parse_cmdline(const char* cmdline)
{
	//--------------------------------------------
	// FUNCTION: parse_cmdline
	// Parses the read line and creates an array with arguments
	// PARAMETERS:
	// cmdline   - input
	// token     - the split input
	// cmdline2  - duplicate of cmdline
	// args_list - the array with arguments
	// del       - delimiter
	// i         - counter
	//----------------------------------------------

	char* token;
	char* cmdline2 = strdup(cmdline);
	char** args_list = (char**)malloc(10000);
	char del[] = " ";
	int i = 0;

	token = strtok(cmdline2, del);
	args_list[i] = token;	

	while (token != NULL) 
	{
		args_list[i] = token;
		i++;
		token = strtok(NULL, del);
	}

	args_list[i] = NULL;

	return args_list;
}


int f_exists(const char* file)
{
	//--------------------------------------------
	// FUNCTION: f_exists
	// Checks whether the file exists
	// PARAMETERS:
	// file      - the file
	// fp        - FILE pointer to the file
	//----------------------------------------------

	FILE *fp;

	fp = fopen(file, "r");

	if (fp == NULL)
		return 0;  // file doesn't exist
	else
		return 1;  // file exists
}

int f_access(const char* file)
{
	//--------------------------------------------
	// FUNCTION: f_access
	// Checks whether the file is readable and executable
	// PARAMETERS:
	// file      - the file
	// acc1      - the value of the first access(is the file readable)
	// acc2      - the value of the second access(is the file executable)
	//----------------------------------------------

	int acc1 = access(file, R_OK);
	int acc2 = access(file, X_OK);

	if (acc1 == 0 && acc2 == 0)
		return 0;	// the file is readable and executable

	if (acc1 == -1 || acc2 == -1)
		return 1;
}

int main()
{
	//--------------------------------------------
	// FUNCTION: main
	// The main body of the program
	// PARAMETERS:
	// cmdline1    - input
	// cmdline     - const input
	// token       - the split input
	// args_array  - the array with arguments
	// pid         - process ID
	// status      - status
	// file_exist  - the value returned by f_exists()
	// file_access - the value returned by f_access()
	//----------------------------------------------

	while(1)
	{
		char* cmdline1 = NULL;
		size_t size = 0;
		getline(&cmdline1, &size, stdin);
		strtok(cmdline1, " \n");
		const char* cmdline = cmdline1;
		char* const* args_array = parse_cmdline(cmdline);
		const char* filename = args_array[0];

		int file_exist = f_exists(filename);
		int file_access = f_access(filename);

		if (file_exist == 0)
			perror(filename);
		
		if (file_exist == 1 && file_access == 1)
			perror(filename);

		pid_t pid = fork();
		int status;

		if (file_exist == 1 && file_access == 0)
		{
			if (pid < 0)
				printf("Forking failed\n");

			if (pid == 0)  // in the child process
			{
				int e_value = execv(filename, args_array); 

				if (e_value < 0)
					printf("Execv error\n");		    	 
			}
	
			if (pid > 0)  // in the parent process
			{
				int w_value = waitpid(pid, &status, 0);

				if (w_value < 0)
					printf("Waitpid error\n");
			}
		}
	}

    return 0;
}