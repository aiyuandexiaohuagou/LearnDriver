#include <stdio.h>
#include <fcntl.h>
#include <sys/poll.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>


int main(int argc, char **argv)
{
	const char *fifo_name = "/home/hujihao/test/fifo_sensor_data";
	const int open_mode = O_WRONLY;  
	if(access(fifo_name, F_OK) == -1) {
		return -1;
	}

	int pipe_fd = open(fifo_name, open_mode);
	if (pipe_fd < 0) {
		perror("open failed\n");
		return -1;
	}

	while (1) {
		static int i = 0;
		char c[9][6] = {"data1", "data2", "data3", "data4","data5","data6","data7","data8","data9"};
		printf("call write()\n");
		ssize_t len = write(pipe_fd, c[i], strlen(c[i]));
		printf("%ld = write(fd, %s, sizeof(c))\n", len, c[i]);
		sleep(1);
		i++;
		if (i==9) {
			break;
		}
	}

	close(pipe_fd);

	return 0;
}
