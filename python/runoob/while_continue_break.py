count = 0
while (count < 9):
	print 'The count is : ', count
	count += 1
print "end!" 

print "This test continue"
count = 0
while (count < 9): 
	count += 1
	if (count % 2 == 0):
		print "count is :", count
	else:
		continue

print "This test break"
count = 0
while (count < 9):
	count += 1;
	if (count == 5):
		print "count == 5, break"
		break
	else:
		print "count != 5, continue"
		continue
