from math import fabs, sqrt

import cv2
from networktables import NetworkTables as NT
import numpy as np

NT.initialize(server='roborio-5472-frc.local')
table = NT.getTable('SmartDashboard')

def blur(frame):
    return cv2.GaussianBlur(frame, (5,5), 0)


def bgr_threshold(frame):
    low_bgr = np.array([0,0,0])
    high_bgr = np.array([200,130,50])
    mask = cv2.inRange(frame, low_bgr, high_bgr)
    thresh_0 = cv2.cvtColor(cv2.bitwise_and(frame, frame, mask=mask), cv2.COLOR_BGR2GRAY)
    return fill_holes(cv2.adaptiveThreshold(thresh_0, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY_INV, 81, 5))

def fill_holes(frame):
    kernel = np.ones((6,6), dtype=np.uint8)
    return cv2.morphologyEx(frame, cv2.MORPH_CLOSE, kernel)

def get_contours(frame):
    frame,cont_list,_ = cv2.findContours(frame, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    return cont_list


def filter_contours(contours):
    cont = contours[0]
    for c in contours:
        if cv2.contourArea(c) > cv2.contourArea(cont):
            cont = c
    return cont

def dist_center(contour, image_size):
    moment = cv2.moments(contour)
    cm_x = moment['m10']/moment['m00']
    return (image_size//2 - cm_x)

def run(frame):
    #1: Blur
    #2: BGR Thresh
    #3: Adaptive Thresh
    #4: Find Contours
    #5: Contour Area Filtering
    #6: Return distance from center of image
    blurry = blur(frame)
    thresholded = bgr_threshold(blurry)
    contours = get_contours(thresholded)
    contours_filtered = filter_contours(contours)
    return dist_center(contours_filtered, frame.shape[0])

if __name__ == '__main__':
    cap = cv2.VideoCapture(0)
    while True:
        ret,frame = cap.read()
        if not ret:
            print('Failed to read from Video Stream')
            #Send a message through network tables notifying the drivers that there is no vision
            break
        table.putNumber('Boiler Dist', run(frame))
    
    #Hey that's pretty good
    #\t-Kyle
    '''
    DIR = os.path.abspath(os.path.join('.', 'LED-Boiler'))
    photo_paths = [os.path.join(DIR, img_path) for img_path in os.listdir(DIR)]
    for num, path in enumerate(photo_paths):
        cv2.namedWindow(str(num))
        img = cv2.imread(path)
        cv2.imshow(str(num), img)
        print(run(img))
        cv2.waitKey(500)
    '''

