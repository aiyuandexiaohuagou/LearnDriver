#include <stdio.h>
#include <fcntl.h>
#include <sys/poll.h>
#include <string.h>
#include <unistd.h>
#include <limits.h> 
#include <sys/stat.h>  
#include <sys/types.h>

int main(int argc, char **argv)
{
	int flag = 0;
	//do {
	const char *fifo_name = "/home/hujihao/test/fifo_sensor_data";
	const int open_mode = O_RDONLY | O_NONBLOCK;  
	if(access(fifo_name, F_OK) == -1) {
		int res = mkfifo(fifo_name, 0777);  
		if(res != 0)  {
			fprintf(stderr, "Could not create fifo %s\n", fifo_name); 
			return -1;
		}
	}

 	printf("Process %d opening FIFO\n", getpid()); 
	int pipe_fd = open(fifo_name, open_mode);
	if (pipe_fd < 0) {
		perror("open failed\n");
		return -1;
	}

	int f = fcntl(pipe_fd, F_GETFL, 0); 
	f &= ~O_NONBLOCK; 
	fcntl(pipe_fd, F_SETFL, f);

	struct pollfd mPollFds[1];
	mPollFds[0].fd = pipe_fd;
	mPollFds[0].events = POLLIN | POLLHUP;	
	mPollFds[0].revents = 0;
	
	while (1) {
		sleep(3);
		int n = poll(mPollFds, 1, -1);
		char buf[100] = {0};
		memset(buf, 0, sizeof(buf));
		read(pipe_fd, buf, 5);
		printf("n=%d, mPollFds[0].revents=%d, buf=%s\n\n\n", n, mPollFds[0].revents, buf);
		
		if (mPollFds[0].revents == POLLHUP) {
			flag = 1; //mPollFds[0].revents = 0; close(pipe_fd); //break;
		} else {
			flag = 0;
		}
		
		mPollFds[0].revents = 0;
		
	};

	//} while(flag);

	

	return 0;
}
