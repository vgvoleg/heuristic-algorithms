import re, sys, json
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import functions as func

def dist(t, pointsFrom, pointsTo, scat):
    pointsNew = []
    for fr,to in zip(pointsFrom, pointsTo):
        pointsNew.append(fr*(1.0 - t)+ to*t)
    scat.set_offsets(pointsNew)

def update_points(i, data, scat):
    pointsFrom = np.array(data[str(i)])
    pointsTo = np.array(data[str(i+1)])

    for t in np.arange(0.0, 1, 0.1):
        dist(t, pointsFrom, pointsTo, scat)
        plt.pause(0.01666)

if __name__ == '__main__':
    pathToFile = sys.argv[1]
    with open(pathToFile) as data_file:    
        data = json.load(data_file)

    populationsCount = data["populationsCount"]
    pointsInPopulation = data["pointsInPopulation"]
    pointSize = data["pointSize"]

    function = sys.argv[2]
    algorithm = sys.argv[3]

    exec("x, y, z = func." + function + "()")
    fig = plt.figure(algorithm + " algorithm to " + function + " function:")
    plt.contour(x, y, z)
    plt.pause(2)

    scatter = plt.scatter([], [], color='blue')
    ani = animation.FuncAnimation(fig, update_points,
                                  frames=np.arange(0, populationsCount - 1),
                                  fargs=(data, scatter),
                                  interval=0,
                                  repeat = False)

    plt.show()
