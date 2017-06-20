package exclusivelock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockExample2 {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File file = new File(tmpdir, "testLocking.txt");
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = rf.getChannel();
        System.out.println("Trying to acquire lock");
        FileLock lock = fileChannel.lock();
        System.out.println("file lock acquired");
        lock.release();

    }
}
