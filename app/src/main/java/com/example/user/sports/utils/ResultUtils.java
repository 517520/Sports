package com.example.user.sports.utils;

/**
 * 主要用户沟通客户端
 * 由于往返客户端的result等信息需要解释，此文档为解释文档
 */

public final class ResultUtils {

    //******************************0.登录*******************************************//

    public static final class Login{
        /**
         * 登录
         */
        public static final String SIGNIN_RESULT_SUCCESS="success";
        //用户不存在
        public static final String SIGNIN_RESULT_FAIL_USERNOTEXIT="fail_user_not_exit";
        //密码错误
        public static final String SIGNIN_RESULT_FAIL_WRONGPASSWORD="fail_wrong_password";


        /**
         * 注册
         */
        //注册成功
        public static final String SIGNUP_RESULT_SUCCESS="success";
        //用户名重复
        public static final String SIGNUP_RESULT_FAIL_USERNAMEREPEAT="fail_username_repeat";
        //数据库添加错误
        public static final String SIGNUP_RESULT_FAIL_DATABASEWRONG="fail_databse_operation_wrong";


        /**
         * 注册后进行初步的个人设置
         */
        //设置成功
        public static final String SET_RESULT_SUCCESS="success";
        //没找到对应user
        public static final String SET_RESULT_FAIL_USERNOTEXIT="fail_user_not_exit";
        //设置时数据库操作错误
        public static final String SET_RESULT_FAIL_DATABASEWRONG="fail_databse_operation_wrong";


        /**
         * 忘记密码
         */
        //设置成功
        public static final String FORGETPASSWORD_RESULT_SUCCESS="success";
        //没找到对应user
        public static final String FORGETPASSWORD_RESULT_FAIL_USERNOTEXIT="fail_user_not_exit";
        //更新时时数据库操作错误
        public static final String FORGETPASSWORD_RESULT_FAIL_DATABASEWRONG="fail_databse_operation_wrong";




    }




    //******************************1.首页*******************************************//

    public static final class Homepage{

    }




    //******************************2.运动*******************************************//

    public static final class Sport{

        /**
         * 存放步行数据
         */
        //如果是步行数据，传过来的Json数据的spoType字段要为walk
        public static final String WALK_SPOTYPE="walk";
        //存放成功
        public static final String WALK_RESULT_SUCCESS="success";
        //添加失败-用户没找到
        public static final String WALK_RESULT_FAIL_USERNOTEXIST="fail_user_not_exist";
        //添加失败-数据库操作错误
        public static final String WALK_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";




        /**
         * 存放跑步数据
         */
        //如果是跑步数据，传过来的Json数据的spoType字段要为run
        public static final String RUN_SPOTYPE="run";
        //存放成功
        public static final String RUN_RESULT_SUCCESS="success";
        //添加失败-用户没找到
        public static final String RUN_RESULT_FAIL_USERNOTEXIST="fail_user_not_exist";
        //添加失败-数据库操作错误
        public static final String RUN_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";




        /**
         * 存放骑行数据
         */
        //如果是骑行数据，传过来的Json数据的spoType字段要为ride
        public static final String RIDE_SPOTYPE="ride";
        //存放成功
        public static final String RIDE_RESULT_SUCCESS="success";
        //添加失败-用户没找到
        public static final String RIDE_RESULT_FAIL_USERNOTEXIST="fail_user_not_exist";
        //添加失败-数据库操作错误
        public static final String RIDE_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";
    }





    //******************************3.运动圈*******************************************//

    public static final class SportActivity{


        /**
         * 创建活动
         */
        //创建活动成功
        public static final String CREATEACTIVITY_RESULT_SUCCESS="success";
        //创建活动失败-数据库操作错误
        public static final String CREATEACTIVITY_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";


        /**
         * 所有活动
         *
         * 活动--返回所有的活动
         *
         */
        //查找所有活动成功
        public static final String ALLACTIVITIES_ALL_RESULT_SUCCESS="success";


        /**
         * 所有活动
         *
         * 活动--返回其中一个活动详情
         *
         */
        //查找单个活动成功
        public static final String ALLACTIVITIES_SINGLE_RESULT_SUCCESS="success";
        //查找失败，该活动不存在
        public static final String ALLACTIVITIES_SINGLE_RESULT_FAIL_NOTEXIST="fail_activity_not_exist";



        /**
         * 所有活动
         *
         * 活动-获取某个活动所有评论
         *
         */
        //json数据中的followId，如果是评论活动则为0，评论他人的评论则为他人评论的ID
        public static final int ALLACTIVITIES_ALLCOMMENTS_FOLLOWID_FOLLOWACTIVITY=0;
        //获取某个活动所有评论成功
        public static final String ALLACTIVITIES_ALLCOMMENTS_RESULT_SUCCESS="success";


        /**
         * 所有活动
         *
         * 活动-在某个活动下进行评论
         *
         */
        //json数据中的followId，如果是评论活动则为0，评论他人的评论则为他人评论的ID
        public static final int ALLACTIVITIES_COMMENT_FOLLOWID_FOLLOWACTIVITY=0;
        //评论成功
        public static final String ALLACTIVITIES_COMMENT_RESULT_SUCCESS="success";
        //评论失败，数据库操作错误
        public static final String ALLACTIVITIES_COMMENT_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";



        /**
         * 所有活动
         *
         * 活动-申请参加某个活动
         *
         */
        //申请加入活动成功
        public static final String ALLACTIVITIES_JOIN_RESULT_SUCCESS="success";
        //申请加入活动失败，我已经加入了这个活动了，无需再次加入了！
        public static final String ALLACTIVITIES_JOIN_RESULT_FAIL_ALREADYIN="fail_already_in_activity";
        //申请加入活动失败，数据库操作错误
        public static final String ALLACTIVITIES_JOIN_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";



        /**
         * 我发布的
         *
         * 我发布的--返回我发布的所有的活动
         *
         */
        //查找所有活动成功
        public static final String MYRELEASE_ALL_RESULT_SUCCESS="success";


        /**
         * 我发布的
         *
         * 我发布的--返回我发布的其中一个活动详情
         *
         */
        //查找单个活动成功
        public static final String MYRELEASE_SINGLE_RESULT_SUCCESS="success";
        //查找失败，该活动不存在
        public static final String MYRELEASE_SINGLE_RESULT_FAIL_NOTEXIST="fail_activity_not_exist";



        /**
         * 我发布的
         *
         * 我发布的-获取我发布的某个活动所有评论
         *
         */
        //json数据中的followId，如果是评论活动则为0，评论他人的评论则为他人评论的ID
        public static final int MYRELEASE_ALLCOMMENTS_FOLLOWID_FOLLOWACTIVITY=0;
        //获取某个活动所有评论成功
        public static final String MYRELEASE_ALLCOMMENTS_RESULT_SUCCESS="success";


        /**
         * 我发布的
         *
         * 我发布的-在我发布的某个活动下进行评论
         *
         */
        //json数据中的followId，如果是评论活动则为0，评论他人的评论则为他人评论的ID
        public static final int MYRELEASE_COMMENT_FOLLOWID_FOLLOWACTIVITY=0;
        //评论成功
        public static final String MYRELEASE_COMMENT_RESULT_SUCCESS="success";
        //评论失败，数据库操作错误
        public static final String MYRELEASE_COMMENT_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";



        /**
         * 我发布的
         *
         * 我发布的-取消我发布的某个活动
         *
         */
        //取消成功
        public static final String MYRELEASE_CANCEL_RESULT_SUCCESS="success";
        //取消活动失败，我不是这个活动的管理者，我没有权限管理这个活动
        public static final String MYRELEASE_CANCEL_RESULT_FAIL_NOAUTHORITY="fail_no_authority";
        //取消活动失败，数据库操作错误
        public static final String MYRELEASE_CANCEL_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";



    }






    //******************************4.联系人*******************************************//

    public static final class LinkPeople{

        /**
         * 1.主界面，一进去就要展示我的联系人列表，以及未读消息数量
         */
        //查找我的所有联系人成功
        public static final String HOME_RESULT_SUCCESS="success";


        /**
         * 2.新的朋友，进入新的朋友请求的页面,在这里可以看到其他人的请求信息
         */
        //查找要添加我为好友的请求成功
        public static final String NEWFRIENDLIST_RESULT_SUCCESS="success";


        /**
         * 3.新的朋友，进入新的朋友请求的页面,可以处理请求信息，接收或者拒绝他人的请求
         */
        //接受他人请求要把acceptOrNot这个字段置为1
        public static final int NEWFRIENDHANDLE_ACCEPTORNOT_ACCRPT=1;
        //拒绝他人请求要把acceptOrNot这个字段置为2
        public static final int NEWFRIENDHANDLE_ACCEPTORNOT_REFUSE=2;


        //接受他人请求并且添加好友成功(只有这种是成功的，下面几种都是各种各样的问题)
        public static final String NEWFRIENDHANDLE_RESULT_ACCEPT_SUCCESS="accept_success";
        //接受他人请求并且但由于他人加我为好友时数据库操作出问题而失败
        public static final String NEWFRIENDHANDLE_RESULT_ACCEPT_FAIL_OTHER="accept_fail_other";
        //接受他人请求并且但由于我加他人为好友时数据库操作出问题而失败
        public static final String NEWFRIENDHANDLE_RESULT_ACCEPT_FAIL_ME="accept_fail_me";
        //不用添加，两个人本来就已经是好友了
        public static final String NEWFRIENDHANDLE_RESULT_ACCEPT_FAIL_ALREADY="accept_fail_alreadyfriends";
        //拒绝好友成功
        public static final String NEWFRIENDHANDLE_RESULT_REFUSE_SUCCESS="refuse_success";
        //处理好友请求失败-未知请求命令，服务器发过来acceptOrNot这个字段数值不清楚
        public static final String NEWFRIENDHANDLE_RESULT_UNKNOW="unknow";



        /**
         * 4.群组，进入群组页面，可以看到有关于我的所有群组
         */
        //查找群组成功
        public static final String GROUP_RESULT_SUCCESS="success";


        /**
         * 5.点击新增群组按钮，添加新的群组
         */
        //新增群组成功
        public static final String CREATEGROUP_RESULT_SUCCESS="success";
        //新增群组失败
        public static final String CREATEGROUP_RESULT_FAIL="fail";



        /**
         * 6.在搜索栏通过用户电话号码搜索陌生人，可以找到对应的陌生人
         */
        //找陌生人成功，且该陌生人以前不是我的好友
        public static final String AFP_RESULT_SUCCESS="success";
        //找陌生人失败，手机号码错误，没有该用户
        public static final String AFP_RESULT_FAIL_OTHERNOTEXIST="fail_other_not_exist";
        //找陌生人失败，虽然找到该用户，但这个人以前就是我的朋友了
        public static final String AFP_RESULT_FAIL_OTHERALREADY="fail_other_already_friend";



        /**
         * 7.在搜索栏通过群组名字找讨论群，可以找到几个同样名字的群
         */
        //找陌生群组成功，且该群组没有与我参加的群组重复
        public static final String AFG_RESULT_SUCCESS="success";
        //找陌生群组失败，群组名字错误，找不到一个群组
        public static final String AFG_RESULT_FAIL_GROUPNOTEXIST="fail_group_not_exist";
        //找陌生群组失败，虽然查找到，但已与我参加的群组重复
        public static final String AFG_RESULT_FAIL_GROUPALREADY="fail_group_already_in";



        /**
         * 8.在搜索栏通过电话找到人之后，点击添加，发送添加请求
         */
        //发送请求成功
        public static final String AAP_RESULT_SUCCESS="success";
        //发送请求失败-数据库操作时错误
        public static final String AAP_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";


        /**
         * 9.在搜索栏通过群组名字找到群后，点击添加，发送添加请求
         */
        //发送请求成功
        public static final String AAG_RESULT_SUCCESS="success";
        //发送请求失败-数据库操作时错误
        public static final String AAG_RESULT_FAIL_DATABASEWRONG="fail_database_wrong";



    }






    //******************************5.我*******************************************//

    public static final class Me{
        /**
         * 获取我的信息
         */
        public static final int DETAIL_SEX_MALE=1;
        public static final int DETAIL_SEX_FEMALE=2;
        public static final String DETAIL_RESULT_SUCCESS="success";
        public static final String DETAIL_RESULT_FAIL="fail";


        /**
         * 更新我的信息
         */
        public static final int UPDATEDETAIL_SEX_MALE=1;
        public static final int UPDATEDETAIL_SEX_FEMALE=2;
        public static final String UPDATEDETAIL_RESULT_SUCCESS="success";
        public static final String UPDATEDETAIL_RESULT_FAIL="fail";



        /**
         * 签到
         */
        public static final String SIGN_RESULT_SUCCESS="success";
        public static final String SIGN_RESULT_FAIL="fail";
    }
}
