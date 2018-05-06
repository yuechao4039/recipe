# java.io.InputStream
1. 所有字节输入流类的父类.
2. `InputStream`的子类必须提供一个方法(从输入流里面读取下一个字节)

```
**public abstract class InputStream**
**extends Object**
**implements Closeable**
```
#### 构造方法
```
public InputStream() {
    
}
```

###### public int available() throws IOException
```
public int available() throws IOException {
        return 0;
}
```

1. 返回当前输入流在下一次方法调用的时候不会阻塞可以读或跳跃的字节数的**_估值_**。
 下一次调用可能是同样的线程，也可能是其他的。 
一次读或者跳过这些字节是不会阻塞的，但是可能会读或者跳过更多的字节。
2. 一些实现会返回当前流中的所有字节，有些不是。所以使用这个返回值来分配一个缓存空间，来存储当前数据流里面的信息是不正确的。 
3. 这个方法子类的实现可以选择抛出一个IOException，当这个输入流已经通过close方法被关闭了。
4. InputStream 的这个方法总是返回0。
5. 这个方法应该被子类覆盖。
6. 当到达输入流的结尾的时候，返回0。

###### public void close() throws IOException

1. 关闭当前输入流，并且释放所有与之关联的系统资源。
2. InputStream的close方法是空的。

####### public void mark​(int readlimit)

1. 在当前输入流里面标记当前位置，通过接下来调用`reset`方法，使数据流归位到最近一次标记的位置，接下来可以重复读取相同字节。

2. readlimit参数告诉当前输入流，在标记位置失效前，最多可以读取多少字节。

3. 是否可以标记，通过`markSupported`返回true来判断。
The general contract of mark is that, if the method markSupported returns true,
 the stream somehow remembers all the bytes read after the call to mark and stands ready to supply those same bytes again 
 if and whenever the method reset is called. However, the stream is not required to remember any data at all 
 if more than readlimit bytes are read from the stream before reset is called.

4. 标记一个已经关闭的数据流，没有任何效果。

5. `InputStream`的`mark`方法没有做任何事情。


###### public boolean markSupported()

1. 测试当前输入流是否支持`mark`和`reset`方法。
2. 无论mark或者reset方法是否支持，他们都是一个流实例的不变属性。
3. InputStream的markSupported返回false。


###### public abstract int read() throws IOException

1. 从输入流中读取下一个字节。字节值会被转为无符号字节值，范围从0到255。如果已经到达流的结尾，返回-1。
输入流不可用，已侦测到流到的结尾，异常被抛出，流会被阻塞。
2. 子类必须提供此方法的具体实现


###### public int read​(byte[] b) throws IOException

1. 从输入流中读取一些字节，并存储在数组b中。
实际读取的字节数据作为一个整数返回。
输入数据不可用，文件结尾补侦测到，或者异常抛出，输入流都会补阻塞。

2.如果数组b的长度是0，将不会有任何字节被读取，方法直接返回0， 否则将会尝试读取至少一个字节。
因为已经到流的结尾，无字节可用，则返回-1，否则，至少一个字节被读，并存储进数组b。

3. 第一个读取的字节存储在b[0]中，下一个存储在b[1]中，以些类推。读取字节的数据，最多等于数组b的长度。
假设k为实际读取字节的数量，这些字节将被存储在 b[0] 到 b[k-1]中，剩余元素b[k]到b[b.length-1]没用。

4. 此方法的效果等同于方法 `read(b, 0, b.length)`。

###### public int read​(byte[] b, int off, int len) throws IOException
1. 从输入流中读取len长度的数据进入b字节数组。
会尽可能去读取len长度的字节，但是一个更小的数据可能被读。实际读取的字节数量将作为整数返回。

2. This method blocks until input data is available, end of file is detected, or an exception is thrown.

3. 如果 len是0， 将没有任何字节被读取，方法返回0，其他情况，将会去尝试读取至少一个字节。
如果因为到已经到文件结尾，没有字节可读，返回-1，否则至少一个字节被读并存入字节数组b。

4. 读取的第一个字节存入数组元素b[off]，下一个元素位置是b[off+1],以此类推。
读取字节的数量最多等于len。假如k是实际读取的字节数，这些字节将被存储在b[off]到b[off+k-1]，b[off+k]到b[off+len-1]无用。

5. In every case, elements b[0] through b[off] and elements b[off+len] through b[b.length-1] are unaffected.

6. 
The read(b, off, len) method for class InputStream simply calls the method read() repeatedly. 

If the first such call results in an IOException, that exception is returned from the call to the read(b, off, len) method. 
If any subsequent call to read() results in a IOException, the exception is caught and treated as if it were end of file; 
the bytes read up to that point are stored into b and the number of bytes read before the exception occurred is returned. 
The default implementation of this method blocks until the requested amount of input data len has been read, 
end of file is detected, or an exception is thrown. 
Subclasses are encouraged to provide a more efficient implementation of this method.

###### public byte[] readAllBytes() throws IOException

1. 读取输入流里面的所有剩余字节。
直到剩余字节已经被读取和流尾已被侦测到或异常抛出，这个方法阻塞。这个方法不会关闭输入流。
当流已经到达末尾，再次调用这个方法将会返回空字节数组。

2. 这个方法预期用来简单情况，一次性读取所有字节到字节数组中。不要读大量数据。

3. 
The behavior for the case where the input stream is asynchronously closed, or the thread interrupted during the read, 
is highly input stream specific, and therefore not specified.

4. If an I/O error occurs reading from the input stream, then it may do so after some, but not all, bytes have been read. 
Consequently the input stream may not be at end of stream and may be in an inconsistent state. 
强烈推荐立即关闭数据，当I/O出现错误。


###### public int readNBytes​(byte[] b, int off, int len) throws IOException



###### public void reset() throws IOException




###### public long skip​(long n) throws IOException
1. 在输入流中跳过或丢弃n个字节的数据。skip方法可能会由于各种原因跳过更小数目的字节，也有可能 是0。
造成这个结果可能是一系列原因，在跳过n个字节前到达文件末尾仅仅是一种可能。
实际跳过的字节数被返回。
如果n是一个负数，这个skip方法问题返回0，没有任何字节被跳过。
子类可能处理这个负数不一样的。

2. skip方法创建一个字节数组，重复读取输入流的字节到数组里去，直到读取n个字节或者到达了流的结尾。
子类建议提供一个更有效率的这个方法的实现。
For instance, the implementation may depend on the ability to seek.



###### public long transferTo​(OutputStream out) throws IOException
1. 从输入流中读取所有字节，按读的顺序写入输出流。一旦方法返回，输入流将结束。这个方法不会关闭任何一下流。
