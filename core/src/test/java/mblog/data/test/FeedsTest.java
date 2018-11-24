package mblog.data.test;

import java.text.MessageFormat;

import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import java.lang.Integer;
import javax.xml.rpc.ParameterMode;
import  mblog.base.utils.MD5;
import org.apache.axis.encoding.XMLType;
import javax.xml.namespace.QName;
import java.util.*;

public class FeedsTest {
    static String pattern = "INSERT INTO mto_feeds (own_id, type, post_id, author_id, created) SELECT user_id, {0}, {1,number,###}, {2,number,###}, now() FROM mto_follows WHERE follow_id = {3,number,###}";


    public static void main(String[] args) {
        /*String sql = MessageFormat.format(pattern, 1, 88888888, 9, 77);

        System.out.println(sql);
        System.out.println(sql);

        try {
            String endpoint = "http://dzdtpt.dzqts.gov.cn/API/ElevatroDataSharing.asmx?op=";
            //直接引用远程的wsdl文件
            //以下都是套路
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName("ElevatorData");//WSDL里面描述的接口名称
            call.addParameter("hashcode", XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.setReturnType(XMLType.XSD_STRING);//设置返回类型
            String temp = MD5.md5("ZDNkM0xuUjVhV0p6TG1OdmJRPT0=TieasyElevatorsharing666");
            String result = (String)call.invoke(new Object[]{temp});
            //给方法传递参数，并且调用方法
            System.out.println("result is "+result);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }*/
        String url="http://dzdtpt.dzqts.gov.cn/API/ElevatroDataSharing.asmx";//提供接口的地址
        Map map = new HashMap();
        String result = null;
        try{
            String Stringurl="http://dzdtpt.dzqts.gov.cn/API/ElevatroDataSharing.asmx?wsdl";
            Map functionMap = new HashMap<>();
            functionMap.put("namespace", "http://tempuri.org/");
            functionMap.put("function", "ElevatorData");
            Map params = new HashMap<>();
            params.put("hashcode", "ecfb41e8f67b3a90fc8c836d3e8a7fdb");
            result = getMessage(Stringurl,functionMap, params);
            map.put("code", "200");
            map.put("data", result);
        }catch(Exception e){
            map.put("code", "300");
            map.put("data", e.getMessage());
            e.printStackTrace();
            System.err.println("获取电梯数据 失败"+e.getMessage());
        }

        System.out.println(result);

    }
    public static  String getMessage(String serviceUrl , Map function ,Map params ){
        String result = null;
        try {
            org.apache.axis.client.Service service = new  org.apache.axis.client.Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(serviceUrl);
            call.setOperationName(new QName(function.get("namespace").toString(),function.get("function").toString()));
            List<Object> list = new ArrayList<>();
            Iterator entries = params.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                call.addParameter(key,
                        org.apache.axis.encoding.XMLType.XSD_STRING,
                        javax.xml.rpc.ParameterMode.IN);// 接口的参数
                list.add(value);
            }
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            call.setSOAPActionURI("http://tempuri.org/ElevatorData");
            result = (String) call.invoke(list.toArray());// 给方法传递参数，并且调用方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
