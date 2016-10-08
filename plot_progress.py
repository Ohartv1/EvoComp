import matplotlib.pyplot as plt

progress = []
with open('progress.txt') as f:
    for l in f.readlines():
    	progress += [float(l)]

speed = []
with open('speed.txt') as f:
    for l in f.readlines():
    	speed += [float(l)]

mean_sd = []
with open('mean_sd.txt') as f:
    for l in f.readlines():
    	mean_sd += [float(l)]

add_ind = []
with open('add_ind.txt') as f:
    for l in f.readlines():
    	add_ind += [float(l)]

mut_inc = []
with open('mut_inc.txt') as f:
    for l in f.readlines():
    	mut_inc += [float(l)]

maximum = []
with open('maximum.txt') as f:
    for l in f.readlines():
    	maximum += [float(l)]

fig = plt.figure()
#plt.subplots_adjust(left = 0.01, right = 0.99, bottom = 0.01)
plt.subplot(2,2,1)
plt.title('Progress')
plt.scatter(range(len(progress)),progress)
plt.axhline(10);
plt.plot(range(len(maximum)), maximum, color='r')
plt.xlim(0,len(progress))

#plt.subplots_adjust(left = 0.01, right = 0.99, bottom = 0.01)
plt.subplot(2,2,2)
plt.title('Speed')
plt.scatter(range(len(speed)),speed)
plt.axhline(1)
plt.xlim(0,len(speed))

plt.subplot(2,2,3)
plt.title('Diversity (red line = individuals added, green line = mutation scaled')
plt.scatter( range(len(mean_sd)), mean_sd)
plt.axhline(0.05)
# show added individuals
for i in add_ind:
	plt.axvline(i,color='r')
# show mutation increased
for i in mut_inc:
	plt.axvline(i,color='y')
plt.xlim(0,len(mean_sd))

plt.subplot(2,2,4)

plt.subplots_adjust(left = 0.02, right = 0.98, bottom = 0.03, top = 0.97, wspace = 0.04, hspace = 0.08)

plt.show()

print(maximum)