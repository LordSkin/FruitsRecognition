import numpy as np
from resizeimage import resizeimage
from PIL import Image

def load_image( infilename ) :
    img = Image.open( infilename )
    img.load()
    img = resizeimage.resize_cover(img, [28, 28])
    data = np.asarray( img, dtype="int32" )
    return data[:,:,0]