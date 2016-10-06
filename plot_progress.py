import matplotlib.pyplot as plt

progress = []
with open('progress.txt') as f:
    for l in f.readlines():
    	progress += [float(l)]

plt.scatter(range(len(progress)),progress)
plt.show()