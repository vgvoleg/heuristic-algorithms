import numpy as np

def doubleextremum():
    x = np.arange(-6, 6, 0.05)
    y = np.arange(-6, 6, 0.05)
    xgrid, ygrid = np.meshgrid(x, y)
    
    zgrid = -3 * xgrid**2 - 4*ygrid**2 - 23*np.cos(xgrid - 0.5)
    return xgrid, ygrid, zgrid

def eckly():
    x = np.arange(-10, 10, 0.05)
    y = np.arange(-10, 10, 0.05)
    xgrid, ygrid = np.meshgrid(x, y)
    
    zgrid = -np.e + 20*np.exp(-np.sqrt((xgrid**2 + ygrid**2)/50)) + np.exp(0.5*(np.cos(2*np.pi*xgrid) + np.cos(2*np.pi*ygrid)))
    return xgrid, ygrid, zgrid

def grivanka():
    x = np.arange(-600, 600, 5)
    y = np.arange(-600, 600, 5)
    xgrid, ygrid = np.meshgrid(x, y)
    
    zgrid = -1 - (xgrid**2 + ygrid**2)/4000 + np.cos(xgrid/np.sqrt(1))*np.cos(ygrid/np.sqrt(2))
    return xgrid, ygrid, zgrid

def multi():
    x = np.arange(-2, 2, 0.05)
    y = np.arange(-2, 2, 0.05)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = xgrid*np.sin(4*np.pi*xgrid) + ygrid*np.sin(4*np.pi*ygrid)
    return xgrid, ygrid, zgrid


def parabaloid():
    x = np.arange(-210, 210, 5)
    y = np.arange(-210, 210, 5)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = -xgrid*xgrid -ygrid*ygrid
    return xgrid, ygrid, zgrid

def rastrigin():
    x = np.arange(-5.12, 5.12, 0.05)
    y = np.arange(-5.12, 5.12, 0.05)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = -20 + (10*np.cos(2*np.pi*xgrid) - xgrid**2)+ (10*np.cos(2*np.pi*ygrid) - ygrid**2)
    return xgrid, ygrid, zgrid

def rosenbrock():
    x = np.arange(-10, 10, 0.05)
    y = np.arange(-10, 10, 0.05)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = -3*(ygrid - xgrid**2)**2 - (1-xgrid)**2
    return xgrid, ygrid, zgrid

def shaffer():
    x = np.arange(-15, 15, 0.1)
    y = np.arange(-15, 15, 0.1)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = 0.5 - (np.sin(np.sqrt(xgrid**2 + ygrid**2))**2 - 0.5) \
                                /(1 + 0.001*(xgrid**2 + ygrid**2))
    return xgrid, ygrid, zgrid

def sinusoidalshvefel():
    x = np.arange(-500, 500, 5)
    y = np.arange(-500, 500, 5)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = xgrid*np.sin(np.sqrt(np.abs(xgrid))) + ygrid*np.sin(np.sqrt(np.abs(ygrid)))
    return xgrid, ygrid, zgrid

def threehump():
    x = np.arange(-5, 5, 0.02)
    y = np.arange(-5, 5, 0.02)
    xgrid, ygrid = np.meshgrid(x, y)

    zgrid = -2*xgrid**2 + 1.05*xgrid**4 - 1 * xgrid**6 /6 - xgrid*ygrid - ygrid**2
    return xgrid, ygrid, zgrid