from sys import argv
from os.path import exists

script, filenameIn, filenameout = argv

txt = open(filenameIn, "r+")
print "file In path:", filenameIn
# print txt.read()
# txt.truncate()
# txt.write("fuck.\nyou")
# print txt.readline()
# txt.close()

dataIn = txt.read()
print "the out file existed:%r" %exists(filenameout)
txtout = open(filenameout, "w")
txtout.write(dataIn)

def print3(*args):
    arg1, arg2, arg3 = args
    print "arg1:%s, arg2:%s, arg3:%s" %(arg1, arg2, arg3)

print3("1","2","3")

def sum(a, b):
    return a + b
print sum(2, 3)

print "python" == "Python"
print 3==3 and 4==5
print 3==3 or 4==5
print not (3==3 or 4==5)

def res(a, b):
    if(a > b):
        return 1
    elif(a < b):
        return -1
    else:
        return 0
print res(8, 9)

a = ["a", "b", "c"]
for num in a:
    print "num:%s" %num

for i in range(0, 6):
    print "num:%d" %i

j = 0
while j < 6:
    if j == 2:
        break
        # exit(0)
    print "num:%d" %j
    j = j+1
print "haha"

print "_".join("haha")

stuff = {'age':10, 'name':'fu', 'sex':'boy'}
print stuff['name']

def test1(a):
    return 10 + a
def test2(a):
    return 20 + a
# func = {'f1':'test1', 'f2':'test2'}
func = {}
func['f1'] = test1
ab = 1
print func['f1'](ab)

# print test1(1)



