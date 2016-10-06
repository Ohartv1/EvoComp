import matplotlib.pyplot as plt

progress = []
with open('progress.txt') as f:
    for l in f.readlines():
    	progress += [float(l)]

speed = []
with open('speed.txt') as f:
    for l in f.readlines():
    	speed += [float(l)]

plt.subplot(2,2,1)
plt.title('Progress')
plt.scatter(range(len(progress)),progress)

plt.subplot(2,2,2)
plt.title('Speed')
plt.scatter(range(len(speed)),speed)
plt.show()