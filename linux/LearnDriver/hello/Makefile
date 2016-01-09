obj-m = hello.o
KVERSION = $(shell uname -r)
all:
	make -C /lib/modules/$(KVERSION)/build M=$(shell pwd)
clean:
	make -C /lib/modules/$(KVERSION)/build M=$(shell pwd) clean



