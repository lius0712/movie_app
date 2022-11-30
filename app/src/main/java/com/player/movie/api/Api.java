package com.player.movie.api;

public class Api {
    //public static final String HOST = "http://10.28.253.144:8090/";
    public static final String HOST = "http://43.143.203.160:8090/";
    public static final String HOSTIMG = "http://43.143.203.160/";
    //查询所有大分类
    public static final String GETUSERDATA = "users/{uid}";
    public static final String GETCATEGORYLIST = "movies/getCategoryList";// 获取分类影片
    public static final String GETKEYWORD = "movies/getKeyWord";//按照classify查询搜索栏的关键词
    public static final String GETALLCATEGORYBYCLASSIFY =  "movies/getAllCategoryByClassify";//按classify大类查询所有catory小类
    public static final String GETALLCATEGORYLISTBYPAGENAME =  "movieCategory/getAllCategoryListByPageName"; //按页面获取要展示的category小类
    public static final String GETUSERMSG =  "/service/movie-getway/getUserMsg"; //获取用户四个指标信息，使用天数，关注，观看记录，浏览记录
    public static final String GETSEARCHRESULT =  "movies/search"; //搜索
    public static final String LOGIN =  "users/login"; //login
    public static final String REGISTER =  "users/"; //register
    public static final String GETSTAR =  "movieStars/getStar"; //获取演员
    public static final String GETMOVIEURL =  "/service/movie/getMovieUrl"; //获取电影播放地址
    public static final String GETVIEWRECORD =  "/service/movie-getway/getViewRecord"; //获取浏览记录
    public static final String SAVEVIEWRECORD =  "/service/movie-getway/saveViewRecord";  //浏览历史
    public static final String GETPLAYRECORD =  "/service/movie-getway/getPlayRecord";  //获取观看记录
    public static final String SAVEPLAYRECORD =  "/service/movie-getway/savePlayRecord";   //播放记录
    public static final String GETFAVORITE =  "/service/movie-getway/getFavorite"; //获取收藏电影
    public static final String SAVEFAVORITE = "/service/movie-getway/saveFavorite"; //添加收藏
    public static final String DELETEFAVORITE = "/service/movie-getway/deleteFavorite"; //删除收藏
    public static final String GETYOURLIKES = "/service/movie/getYourLikes";//猜你想看
    public static final String GETRECOMMEND = "movies/getRecommend";//获取推荐
    public static final String ISFAVORITE = "/service/movie-getway/isFavorite";//查询是否已经收藏
    public static final String UPDATEUSER = "/service/movie-getway/updateUser";//更新用户信息
    public static final String UPDATEPASSWORD = "/service/movie-getway/updatePassword";//更新密码
    public static final String hotMovie = "/movieNetwork/getTopMovieList"; //获取热度电影

}
