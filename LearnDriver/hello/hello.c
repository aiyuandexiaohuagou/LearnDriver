#include <linux/init.h>
#include <linux/module.h>

MODULE_LICENSE("GPL");  
MODULE_AUTHOR("liufei_learning");  
MODULE_DESCRIPTION("Hello world module");  


static int __init hello_init(void)
{
	printk(KERN_ALERT "# This is My first driver: hello driver init!\n");
	return 0;
}


static void __exit hello_exit(void)
{

	printk(KERN_ALERT "# hello driver exit\n");
}

module_init(hello_init);
module_exit(hello_exit);


