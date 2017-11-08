package guo.ping.db2pojo.tools;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ParseConfigXml {

    public static void changeConfigXml(String dbUrl, String username, String psd,
                                       String outputPath, String[] tablesArr) throws DocumentException {

        Document document = DocumentHelper.parseText(ConfigXmlString.CONTENT);
        Element rootElement = document.getRootElement();
        Element context = rootElement.element("context");


        Element jdbcConnection = context.element("jdbcConnection");
        Attribute connectionURL = jdbcConnection.attribute("connectionURL");
        connectionURL.setValue(dbUrl);

        Attribute userId = jdbcConnection.attribute("userId");
        userId.setValue(username);

        Attribute password = jdbcConnection.attribute("password");
        password.setValue(psd);


        String[] outputPathArrs = outputPath.split("&");
        String outFile = outputPathArrs[0];     //文件夹
        String outPackage = outputPathArrs[1];  //包

        Element javaModelGenerator = context.element("javaModelGenerator");
        Attribute targetPackage1 = javaModelGenerator.attribute("targetPackage");
        Attribute targetProject1 = javaModelGenerator.attribute("targetProject");
        targetPackage1.setValue(outPackage + ".model");
        targetProject1.setValue(outFile);

        Element sqlMapGenerator = context.element("sqlMapGenerator");
        Attribute targetPackage2 = sqlMapGenerator.attribute("targetPackage");
        Attribute targetProject2 = sqlMapGenerator.attribute("targetProject");
        targetPackage2.setValue(outPackage + ".mapper");
        targetProject2.setValue(outFile);

        Element javaClientGenerator = context.element("javaClientGenerator");
        Attribute targetPackage3 = javaClientGenerator.attribute("targetPackage");
        Attribute targetProject3 = javaClientGenerator.attribute("targetProject");
        targetPackage3.setValue(outPackage + ".mapper");
        targetProject3.setValue(outFile);


        //删除前先清空所有table节点
        List<Element> tableList = context.elements("table");
        for (Element table : tableList) {
            table.getParent().remove(table);
        }


        for (String table : tablesArr) {
            Element tableElement = context.addElement("table");
            tableElement.addAttribute("tableName", table);
        }

        //保存文件
        writerDocumentToNewFile(outputPath, document);
    }

    /**
     * document写入新的文件
     */
    private static void writerDocumentToNewFile(String path, Document document) {
        //输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码
        format.setEncoding("UTF-8");
        //XMLWriter 指定输出文件以及格式
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File(path.split("&")[0] + "/" + "config.xml")), "UTF-8"), format);
            //写入新文件
            writer.write(document);
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 遍历当前节点下的所有节点
     */
    private static void listNodes(Element node) {
        System.out.println("当前节点的名称：" + node.getName());
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //遍历属性节点
        for (Attribute attribute : list) {
            System.out.println("属性" + attribute.getName() + ":" + attribute.getValue());
        }
        //如果当前节点内容不为空，则输出
        if (!(node.getTextTrim().equals(""))) {
            System.out.println(node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
