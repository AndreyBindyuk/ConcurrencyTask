package exclusivelock;

import sun.security.action.GetPropertyAction;

import static java.security.AccessController.doPrivileged;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;

public class FileLockExample2 {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        Path tempDir = Paths.get(doPrivileged(new GetPropertyAction(
                "java.io.tmpdir")));
        File file = new File(tempDir.toAbsolutePath() + File.separator
                + "testLocking.txt");
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = rf.getChannel();
        System.out.println("Trying to acquire lock");
        FileLock lock = fileChannel.lock();
        System.out.println("file lock acquired");
        lock.release();

    }
}
