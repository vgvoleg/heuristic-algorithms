import functions as func
import re, sys, json
import matplotlib.pyplot as plt
from time import sleep

if __name__ == '__main__':
    pathToFile = "../files/" + sys.argv[1]
    with open(pathToFile) as data_file:    
    	data = json.load(data_file)

    populationsCount = data["populationsCount"]
    pointsInPopulation = data["pointsInPopulation"]
    pointSize = data["pointSize"]

    function = re.findall(r'(?<=files/).*(?=_)', pathToFile)
    algorithm = re.findall(r'(?<=_).*(?=\.json)', pathToFile)

    exec("x, y, z = func." + function[0] + "()")
    fig = plt.figure(algorithm[0] + " algorithm to " + function[0] + " function:")
    plt.contour(x, y, z)
    plt.ion()
    plt.pause(3)
    for i in range(populationsCount):
        points = data[str(i)]
        scat = plt.scatter(*zip(*points))
        plt.draw()
        plt.pause(0.01)
        scat.remove()

    while True:
    	plt.pause(0.5)
    plt.show()