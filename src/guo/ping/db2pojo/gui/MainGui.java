package guo.ping.db2pojo.gui;

import guo.ping.db2pojo.exception.InputException;
import guo.ping.db2pojo.tools.ClearFiles;
import guo.ping.db2pojo.tools.Generator;
import guo.ping.db2pojo.tools.ParseConfigXml;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui {
    private JTextField dbUrlText;
    private JTextField usernameText;
    private JTextField psdText;
    private JButton outputPathButton;
    private JTextField outputText;
    private JTextField tablesText;
    private JButton clearButton;
    private JButton confirmButton;
    private JPanel rootPanel;

    private String dbUrl = "jdbc:mysql://localhost:3306/your database";
    private String username = "root";
    private String password = "";
    private String outputPath = "";
    private String tables = "";
    private String[] tablesArr;

    public MainGui() {
        //打开文件目录
        outputPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();             //设置选择器
                chooser.setFileSelectionMode(1);
                int returnVal = chooser.showOpenDialog(outputPathButton);        //是否打开文件选择框

                if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型

                    String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径
                    outputPath = filepath;

                    outputText.setText(filepath + "&");

                }
            }
        });

        //清除
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dbUrl.isEmpty()&&!username.isEmpty()&&!password.isEmpty()&&!outputPath.isEmpty()&&!tables.isEmpty()){
                    dbUrlText.setText(dbUrl);
                    usernameText.setText(username);
                    psdText.setText(password);
                    outputText.setText(outputPath);
                    tablesText.setText(tables);
                }else {
                    dbUrlText.setText("jdbc:mysql://localhost:3306/your db");
                    usernameText.setText("root");
                    psdText.setText("");
                    outputText.setText("路径&包名，如 C:/demo&guo.ping.hello");
                    tablesText.setText("所有表名，以&分隔");
                }



                if (ClearFiles.clearPathFiles(getClearOutputPath(outputPath))) {
                    popInfoMessage("", "文件删除成功！");
                } else {
                    popWarningMessage("", "文件删除失败！");
                }
            }
        });

        //确定
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    getWidgetText();

                    //根据用户输入修改配置文件
                    ParseConfigXml.changeConfigXml(dbUrl, username, password, outputPath, tablesArr);

                    //反向工程生成
                    Generator.generator(getGenerateConfigFile(outputPath));

                    popInfoMessage("", "生成成功！");

                } catch (InputException e1) {
                    popWarningMessage("", "数据输入错误，请仔细查看！");
                } catch (Exception e1) {
                    e1.printStackTrace();
                    popWarningMessage("", "生成错误！");
                }

            }
        });
    }

    private String getClearOutputPath(String outputPath) {
        try {
            String parentFiles = outputPath.split("&")[0];
            String childFiles = (outputPath.split("&")[1]).replace(".","&").split("&")[0];
            return parentFiles + "/" + childFiles;
        } catch (ArrayIndexOutOfBoundsException e){
            popWarningMessage("", "路径错误，无法删除文件！");
        }
        return "";
    }

    private String getGenerateConfigFile(String outputPath){
        try {
            String parentFiles = outputPath.split("&")[0];
            return parentFiles + "/" + "config.xml";
        } catch (ArrayIndexOutOfBoundsException e){
            popWarningMessage("", "路径错误，无法生成配置文件！");
        }
        return "";
    }

    /**
     * 获取所有文本框的文本值
     */
    private void getWidgetText() throws InputException {
        dbUrl = dbUrlText.getText();
        username = usernameText.getText();
        password = psdText.getText();
        outputPath = outputText.getText();
        tables = tablesText.getText();
        if (tables.contains(" ") || outputPath.contains(" ") || !dbUrl.contains("jdbc:mysql://localhost:3306/") || username.isEmpty() || password.isEmpty()) {
            throw new InputException("数据输入错误");
        }

        tablesArr = tables.split("&");
    }

    private void popInfoMessage(String title, String context) {
        JOptionPane.showMessageDialog(null, context, title, JOptionPane.PLAIN_MESSAGE);
    }

    private void popWarningMessage(String title, String context) {
        JOptionPane.showMessageDialog(null, context, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("MySQL数据库表反向工程工具V1.0");
        frame.setContentPane(new MainGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    }
}
