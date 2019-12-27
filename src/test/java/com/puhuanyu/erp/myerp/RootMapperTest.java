package com.puhuanyu.erp.myerp;

import com.puhuanyu.erp.myerp.bean.*;
import com.puhuanyu.erp.myerp.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RootMapperTest
{
    @Autowired
    private RootTypeMapper rootTypeMapper;
    @Autowired
    private RootMapper rootMapper;
    @Autowired
    private ShellMapper shellMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private ClientinfoMapper clientinfoMapper;
    @Autowired
    private GoodsinfoMapper goodsinfoMapper;
    @Test
    public void RootType()
    {
        //rootTypeMapper.doRootType(new Roottype("技术管理"));
        //rootTypeMapper.deleteRootType(7);
        //rootTypeMapper.updateRootType(new Roottype(1,"信息管理"));
        Roottype roottype=rootTypeMapper.findRootTypeById(1);
        System.out.println(roottype.toString());
        List<Roottype> list=rootTypeMapper.findRootTypeByAll();
        for(Roottype t:list)
        {
            System.out.println(t.toString());
        }
    }
    @Test
    public void Warehouse()
    {
        //warehouseMapper.doWarehouse(new Warehouse("废料仓","天河区",empMapper.findById(1001001)));
        // warehouseMapper.deleteWarehouse(6);
        //warehouseMapper.updateWarehouse(new Warehouse(6,"垃圾仓","白云区",empMapper.findById(1001001)));
        System.out.println(warehouseMapper.findWarehouseById(1).toString());
        System.out.println(warehouseMapper.findWarehouseByAll().get(0).toString());
    }

    @Test
    public void Root()
    {
        //rootMapper.doRoot(new Root(701,"修改密码",rootTypeMapper.findRootTypeById(1)));
        //rootMapper.updateRoot(new Root(701,"添加员工",rootTypeMapper.findRootTypeById(2)));
        rootMapper.deleteRoot(701);
        //System.out.println(rootMapper.findRootById(101).toString());
        //System.out.println(rootMapper.findRootByAll().get(0).toString());
        //System.out.println(rootMapper.findRootByTypeId(1).get(1).toString());
    }

    @Test
    public void Shell()
    {
        //shellMapper.doShell(new Shell("20192000003","2019-12-25","2019-12-27",clientinfoMapper.findById(10101),empMapper.findById(3001001),goodsinfoMapper.findGoodsinfoByid(10001),100,0));
        //shellMapper.updateShell(new Shell(3,"20192000003","2019-12-25","2019-12-28",clientinfoMapper.findById(10101),empMapper.findById(3001001),goodsinfoMapper.findGoodsinfoByid(10001),200,1));
        //shellMapper.deleteShellById(3);
        System.out.println(shellMapper.findShellById(1).toString());
        System.out.println(shellMapper.findShellByNumberid("20192000002"));
        System.out.println(shellMapper.findShellBybegaindate("2019-02-28",0,1).get(0).toString());
        System.out.println(shellMapper.findShellByoverdate("2019-05-20",0,1).get(0).toString());
        System.out.println(shellMapper.findShellByState(1,0,1).get(0).toString());
        System.out.println(shellMapper.findShellAll(0,1).get(0).toString());
    }
    @Test
    public void ShellBy()
    {
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("begaindate","'2019-02-28'");
        map.put("emp_id",3001001);
        System.out.println(shellMapper.findShellByCondition("shell",map ,0,1));

    }
    @Test
    public void button()
    {
        int i,p=1,s=0;
        for(i=1;i<=7;i++)
        {
            s=s+p;
            p=p*2;
            System.out.println("第"+i+"次循环：s="+s+"  p="+p);
        }
        System.out.println(s);
    }
    @Test
    public void boot()
    {
        int[][] a=new int[3][3];
        int s=0,i,j;
        for(i=0;i<3;i++)
        {
            for (j=0;j<3;j++)
            {
                a[i][j]=i*3+j+1;
            }
        }
        String text="";
        for(i=0;i<3;i++)
        {
            for (j=0;j<=i;j++)
            {
                text+=a[i][j];
            }
        }

        System.out.println(text);
    }
}
