## 踩坑记录

1. Mybatis递归查找返回树形结构

   * 实体类PermissionTree

     ```java
     public class PermissionTree {
         @JSONField(ordinal = 1)
         private Integer id;
         @JSONField(ordinal = 2)
         private String authName;
         //    @JSONField(ordinal = 5)
         @JSONField(serialize = false)
         private String level;
         @JSONField(ordinal = 3)
         private String path;
         @JSONField(ordinal = 4)
         private Integer pid;
         @JSONField(ordinal = 6)
         private List<PermissionTree> children;
     }
     /**
       *  省略getter  setter
       **/
     ```

   * 查找语句

     定义结果集BaseResultMapTree, 对应实体类, 其中children使用collection集合, 设置其返回类型以及递归查找语句`getChildrenByPid`, 返回该权限的所有子权限集合. 

     `getAllPermissionTree`中根据业务需求添加条件, 去除重复的子权限(即只选择没有父权限或父权限为本身的结果)

     ```xml
         <resultMap id="BaseResultMapTree" type="com.zyq.shopserver.system.entity.PermissionTree">
             <id property="id" jdbcType="SMALLINT" column="ps_id"/>
             <result property="authName" jdbcType="VARCHAR" column="ps_name"/>
             <result property="level" jdbcType="VARCHAR" column="ps_level"/>
             <result property="path" jdbcType="VARCHAR" column="ps_api_path"/>
             <result property="pid" jdbcType="SMALLINT" column="ps_pid"/>
             <collection property="children" ofType="BaseResultMapTree" column="ps_id" select="getChildrenByPid" javaType="java.util.ArrayList"></collection>
         </resultMap>
     <select id="getChildrenByPid" resultMap="BaseResultMapTree">
         select a.*, b.ps_api_path from sp_permission a, sp_permission_api b 
         where a.ps_pid = #{ps_id}
     </select>
     <select id="getAllPermissionTree" resultMap="BaseResultMapTree">
         select a.*, b.ps_api_path from sp_permission a, sp_permission_api b 
         where a.ps_id=b.ps_id and (a.ps_pid = a.ps_id or a.ps_pid = 0)
     </select>
     ```

   2. 返回对象自动转换成json, 过滤其中某些属性:

      在需要过滤的属性上添加`@JSONField(serialize = false)`注解(当使用fastjson生成json时)

      在类上添加`@JsonIgnoreProperties(value = {"ps_level"})`注解以及在getter方法上添加`@JsonIgnore`注解和`JsonView`是使用springboot自动生成json时使用.

   