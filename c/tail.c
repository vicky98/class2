//--------------------------------------------
// NAME: Victoria Admasu
// CLASS: XI A
// NUMBER: 6
// PROBLEM: #1
// FILE NAME: tail.c
// FILE PURPOSE:
// Implementation of the UNIX command 'tail'
//---------------------------------------------

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char *argv[])
{

//--------------------------------------------
// PARAMETERS:
// char buff[2000]     - buffer
// int fd              - file descriptor
// int i = 1       	   - counter
// int status          - the number of bytes of the file
// int bytes_written   - the number of written bytes 
// int status_write    - the number of bytes we write
//----------------------------------------------

	char buff[2000];
	int fd;
	int i = 1;
	int status;
	int bytes_written = 0;
	
	for (i = 1; i <= argc; i++)
	{
	
	// checks whether the files exist
		fd = open(argv[i], O_RDONLY);
		char[] filename = argv[i];

		if (fd < 0)
		{
			perror("tail: cannot open '%c' for reading", filename);
			return 0;
		}
		
	// for reading from the standard input /not finished/	
		if (argv[i] == '-')
		{
			bytes_written = 0;

			while ((status = read(STDIN_FILENO, buff, 2000)) > 0)
			{
				while (10 * bytes_written < status)
				{
					int status_write = write(STDOUT_FILENO, buff + bytes_written, status - bytes_written);

					bytes_written += status_write;
				}
			}
		}

		if (fd > 0)
		{
			while ((status = read(fd, buff, 2000)) > 0)
			{
				bytes_written = 0;
				
				while (10 * bytes_written < status)
				{
					int status_write = write(STDOUT_FILENO, buff + bytes_written, status - bytes_written);

					bytes_written += status_write;
				}
	
				close(fd);

				return 0;
			}
		}
	}

	close(fd);

	return 0;
}