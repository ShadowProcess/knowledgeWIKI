package com.example.sharding.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_info")
@ApiModel(value="UserInfo对象", description="用户信息表")
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;
    /**
     * 所谓加Salt，就是加点“佐料”。当用户首次提供密码时（通常是注册时），
     * 由系统自动往这个密码里加一些“Salt值”，这个值是由系统随机生成的，
     * 并且只有系统知道。然后再散列。而当用户登录时，
     * 系统为用户提供的代码撒上同样的“Salt值”，然后散列，再比较散列值，已确定密码是否正确。 　　
     * 这样，即便两个用户使用了同一个密码，由于系统为它们生成的salt值不同，
     * 他们的散列值也是不同的。即便黑客可以通过自己的密码和自己生成的散列值来找具有特定密码的用户，
     * 但这个几率太小了（密码和salt值都得与黑客使用的一样才行）。
     *
     * 那该怎么办呢？
     * 所以，不但要加盐，而且每个用户的盐还得不一样。
     * 我看到的一种做法是，直接用每个用户的用户名，或者用户名的变形来做盐。
     */


    @ApiModelProperty(value = "性别 0未知 1女 2男")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "姓名")
    @TableField("user_name")
    private String userName;
}