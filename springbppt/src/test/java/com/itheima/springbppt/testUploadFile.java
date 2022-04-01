package com.itheima.springbppt;

import com.itheima.springbppt.fastdfs.FastDFSClient;
import com.itheima.springbppt.fastdfs.FastDFSClientUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * fastdfs测试
 */
@SpringBootTest
public class testUploadFile {

    @Autowired
    private FastDFSClient client;

    @Autowired
    private FastDFSClientUtil fastDFSClientUtil;

    @Value("${web-server-url}")
    private String fileServerUrl;   //fastdfs图片url前半部分


    /**
     * File转成MockMultipartFile格式
     *
     * @throws IOException
     */
    @Test
    public void testUpload02() throws IOException {
        File file = new File("C:\\Users\\就不告诉你\\Desktop\\aa.txt");
        FileInputStream inputStream = new FileInputStream(file);

        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", inputStream);
        String uploadFile = fastDFSClientUtil.uploadFile(multipartFile);
        String filename = multipartFile.getOriginalFilename();
        System.out.println(filename);   //aa.txt
        System.out.println(multipartFile.getName());    //file
        System.out.println(multipartFile);  //org.springframework.mock.web.MockMultipartFile@7cfb4736
        System.out.println(uploadFile); //group1/M00/00/00/wKgXgWIEunmACCuwAAAAAAAAAAA416.txt


    }

    /**
     * fastdfs删除
     */
    @Test
    public void testUpload06() {
        String fileId = "group1/M00/00/00/wKgXk2FygLyANnv3AAadrz7KKyw796.jpg";
        fastDFSClientUtil.delFile(fileId);
    }

    /**
     * 路径参数
     */
    @Test
    public void testUpload() {
        File file = new File("C:\\Users\\就不告诉你\\Pictures\\Saved Pictures\\aaa.jpg");
        String fileName = file.getName();
        String filePath = file.getPath();
        String fileParent = file.getParent();

        System.out.println(fileName);   //aaa.jpg
        System.out.println(filePath);   //C:\Users\就不告诉你\Pictures\Saved Pictures\aaa.jpg
        System.out.println(fileParent); //C:\Users\就不告诉你\Pictures\Saved Pictures
    }

    @Test
    public void testUpload08() {
        String name = "http://192.168.23.129/group1/M00/00/00/wKgXgWILGG2AUyqdAAIAHSIk1CY393.pdf";
        String[] group1s = name.split("group1");
        for (String group1 : group1s) {
            System.out.println(group1);
            //http://192.168.23.129/
            ///M00/00/00/wKgXgWILGG2AUyqdAAIAHSIk1CY393.pdf
        }
    }

    /**
     * 查询fastdfs数据
     *
     * @throws IOException
     * @throws MyException
     */
    @Test
    public void testUploadFile() throws IOException, MyException {
        //  1.读取fastdfs配置文件
//        ClientGlobal.initByProperties("fastdfs-client.conf");
        ClientGlobal.init(new ClassPathResource("fastdfs-client.conf").getPath());
        System.out.println(ClientGlobal.getG_network_timeout());
        System.out.println(ClientGlobal.getG_connect_timeout());

        //  2.创建TrackerClient
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getTrackerServer();
        System.out.println(trackerServer);

        //  3.创建StorageServer
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        System.out.println(client);

        //  4.查询fastdfs文件
        FileInfo fileInfo1 = client.query_file_info("group1", "M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg");
        FileInfo fileInfo2 = client.query_file_info1("group1/M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg");
        FileInfo fileInfotxt = client.query_file_info1("group1/M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg");

        String string = fileInfo1.toString();
        System.out.println(string);
        System.out.println(fileInfo2);
        System.out.println(fileInfotxt);
    }


    @Test
    public void testUpload09() throws IOException, MyException {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);///D:/java/idea/IdeaProjects/sTUDENTBOOT/git-test/springbppt/target/test-classes/
        System.out.println("=======================");

        String path1 = new ClassPathResource("fastdfs-client.conf").getPath();
        System.out.println(path1);
        ClientGlobal.init(path1);
        System.out.println(ClientGlobal.getG_connect_timeout());
        System.out.println(ClientGlobal.getG_network_timeout());
    }


    @Test
    public void testUpload10() throws IOException, MyException {
        //1.读取fastdfs配置文件
        ClientGlobal.init("fastdfs-client.conf");

        //2.创建tracker客户端对象
        TrackerClient tracker = new TrackerClient();
        //3.获取tracker服务端对象
        TrackerServer trackerServer = tracker.getTrackerServer();
        //获取storage服务端对象
        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
        //创建storage客户端对象
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //下载文件
        byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg");
        System.out.println(bytes);
        //创建IO流对象进行往外输写进行下载
        FileOutputStream stream = new FileOutputStream("D:\\data\\aa\\aa.jpg");
        stream.write(bytes);

        stream.flush();
        stream.close();

    }

    @Test
    public void testUpload11() {
        String url = "http://192.168.23.129/group1/M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg";
        String[] split = url.split(fileServerUrl);
        System.out.println("a=" + split[1]);

        String name = "group1/M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg";
        String[] strings = name.split("\\/", 2); //后面的数字是要返回的字符串个数,
        System.out.println(strings[0]); // group1
        System.out.println(strings[1]); // M00/00/00/wKgXgWJD9raAKB9NAAUAiwTCXqk063.jpg

    }
}
