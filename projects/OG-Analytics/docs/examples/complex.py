print "starting..."

### @export "import"
from com.opengamma.math.number import ComplexNumber

### @export "define"
cn = ComplexNumber(2, 3)

### @export "get-real"
print cn.getReal()

### @export "get-imaginary"
cn.getImaginary()

### @export "to-string"
cn.toString()

