
# TensorFlow and tf.keras
import tensorflow as tf
from Images_operations import load_image
import os
from tensorflow import keras
from resizeimage import resizeimage

# Helper libraries
import numpy as np
import matplotlib.pyplot as plt

print(tf.__version__)


#prepare train images and labels

train_images_banana = np.empty([480, 28, 28])
train_labels_banana = np.empty([480])
train_labels_banana.fill(0)


counter =0
for root, dirs, files in os.walk("/Fruits/Banana/"):
    for filename in files:
        im = load_image("Fruits/Banana/"+filename)
        train_images_banana[counter,:,:]= im
        counter = counter+1



train_images_pear = np.empty([482, 28, 28])
train_labels_pear = np.empty([482])
train_labels_pear.fill(1)


counter =0
for root, dirs, files in os.walk("/Fruits/Pear/"):
    for filename in files:
        im = load_image("/Fruits/Pear/"+filename)
        train_images_pear[counter,:,:]= im
        counter = counter+1


train_images_raspberry = np.empty([480, 28, 28])
train_labels_raspberry = np.empty([480])
train_labels_raspberry.fill(2)


counter =0
for root, dirs, files in os.walk("/Fruits/Raspberry/"):
    for filename in files:
        im = load_image("/Fruits/Raspberry/"+filename)
        train_images_raspberry[counter,:,:]= im
        counter = counter+1

train_images = np.concatenate((train_images_banana, train_images_pear), axis=0)
train_images = np.concatenate((train_images, train_images_raspberry), axis=0)

train_images = train_images /250.0

train_labels = np.append(train_labels_banana, train_labels_pear)
train_labels = np.append(train_labels, train_labels_raspberry)

#prepare test images and labels

test_images_banana = np.empty([10, 28, 28])
test_labels_banana = np.empty([10])
test_labels_banana.fill(0)


counter =0
for root, dirs, files in os.walk("/Fruits/test/Banana/"):
    for filename in files:
        im = load_image("/Fruits/test/Banana/"+filename)
        test_images_banana[counter,:,:]= im
        counter = counter+1



test_images_pear = np.empty([10, 28, 28])
test_labels_pear = np.empty([10])
test_labels_pear.fill(1)


counter =0
for root, dirs, files in os.walk("/Fruits/test/Pear/"):
    for filename in files:
        im = load_image("/Fruits/test/Pear/"+filename)
        test_images_pear[counter,:,:]= im
        counter = counter+1


test_images_raspberry = np.empty([480, 28, 28])
test_labels_raspberry = np.empty([480])
test_labels_raspberry.fill(2)


counter =0
for root, dirs, files in os.walk("/Fruits/test/Raspberry/"):
    for filename in files:
        im = load_image("/Fruits/test/Raspberry/"+filename)
        test_images_raspberry[counter,:,:]= im
        counter = counter+1

test_images = np.concatenate((test_images_banana, test_images_pear), axis=0)
test_images = np.concatenate((test_images, test_images_raspberry), axis=0)

test_images = test_images /250.0

test_labels = np.append(test_labels_banana, test_labels_pear)
test_labels = np.append(test_labels, test_labels_raspberry)




model = keras.Sequential([
    keras.layers.Flatten(input_shape=(28, 28)),
    keras.layers.Dense(128, activation=tf.nn.relu),
    keras.layers.Dense(10, activation=tf.nn.softmax)
])

model.compile(optimizer=tf.train.AdamOptimizer(),
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(train_images, train_labels, epochs=5)

response = model.predict(train_images)

test_loss, test_acc = model.evaluate(test_images, test_labels)

print('Test accuracy:', test_acc)


