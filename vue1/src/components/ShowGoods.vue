<template><div></div></template>

<script>
import axios from 'axios'
export default{
    data(){
        return{
            currentUser:null,


        }
    },
    created(){

    },
    computed:{
        isLoggedIn(){
            return !!this.currentUser;
        }

    },
    methods:{
        
    }

}


</script>



<!-- 未登录框 -->
<style type="text/css" scoped>
.custom-input {
    margin-right: 10px;
    width: 407.49px; /* 设置宽度 */
    height: 45px; /* 设置高度 */
    background-color: #FFFFFF; /* 设置背景颜色 */
    padding: 10px; /* 设置内边距 */
    border: 1px solid #ccc; /* 设置边框，如果需要的话 */
    box-sizing: border-box; /* 保证总宽度包括了内边距和边框 */
    /* 添加其他任何需要的样式 */
}
#search_kind {
    margin-right: 10px;
    width: 76.8px; /* 设置宽度 */
    height: 45px; /* 设置高度 */
    background-color: #FFFFFF; /* 设置背景颜色为白色 */
    color: #000000; /* 设置文字颜色为黑色 */
    font-size: 13.333px; /* 设置字体大小 */
    font-family: Arial, sans-serif; /* 设置字体为Arial，备用字体为sans-serif */
    border: 1px solid #ccc; /* 如果需要，可以设置边框 */
    box-sizing: border-box; /* 内容、内边距和边框的总宽度和高度 */
}
.custom-button {
    width: 89.04px; /* 设置宽度 */
    height: 45px; /* 设置高度 */
    background-color: #F0F0F0; /* 设置背景颜色为浅灰色 */
    padding: 1px 6px; /* 垂直内边距1px，水平内边距6px */
    border: none; /* 假设您想去掉默认边框 */
    font-family: Arial, sans-serif; /* 假设您想要的字体是Arial，备用字体是sans-serif */
    color: #000000; /* 文字颜色，假设您想要黑色，与您的选择框类似 */
    box-sizing: border-box; /* 内边距和边框包含在元素的总宽度和高度内 */
    cursor: pointer; /* 更改光标样式，表示这个元素是可点击的 */
}
.else{
position:absolute;
top:40%;
left:50%;
transform:translate(-50%,-50%);
width:450px;
padding:30px;
background: rgba(224,224,224,.8);
box-sizing:border-box;
box-shadow: 0px 15px 25px rgba(0,0,0,.5);
border-radius:16px;
text-align:center;
font-family:KaiTi;
font-size:26px;
}
a{
	text-decoration:none;
}
</style>
<style scoped>
.centered-container {
        display: flex;
        justify-content: center;
        align-items: center;
}
.container {
        font-family: Arial, sans-serif;
        width: 80%;
        margin: 2% auto;
        border: 1px solid #ccc;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    th, td {
        border: 1px solid #ccc;
        padding: 10px;
        
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    img {
        width: 50px;
        height: auto;
    }

    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 20px;
    }

    .history-btn, .prev, .next {
        padding: 10px 20px;
        color: #fff;
        border: none;
        cursor: pointer;
        margin: 0 5px;
    }
    
    .history-btn {
        background-color: rgb(237, 137, 108);
        border-radius: 8px;
    }
    
    .prev, .next {
        background-color: rgb(237, 196, 110);
    }

    .history-btn:hover, .prev:hover, .next:hover {
        background-color: #d32f2f;
    }
    
    .history-btn a {
        text-decoration: none;
        color: white;
    }
    
    .history-btn a:hover {
        text-decoration: none; 
    }
    .left-btn-container {
        margin-right: auto;
        display: flex;
        align-items: center;
	}
	form {
    display: flex; /* 让表单内的元素在同一行显示 */
       width:600px;
    height:45px;
	}
	
	input[type="text"] {
        flex-grow: 1; /* 让搜索框占据剩余的空间 */
	}
	input[name="keyword"] {
        width: 100%;
        padding: 10px; 
        border: 1px solid #ccc; 
        border-radius: 4px;
	}	
	#search_list {
		position: fixed;
		top: 140px;
		left: 12%;
		width: 70%;
		background-color: white;
	}

	
	#search_list div {
        border-bottom: 1px solid black; 
	}
	
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
}

.close:hover {
    color: #000;
    text-decoration: none;
}

    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
    
    .price-modal-content {
    background-image: url('~@/assets/img/a.jpg'); /* 更改为您自己的图片路径 */
    background-size: cover; /* 调整背景图片大小以填充整个容器 */
    background-position: center; /* 居中显示背景图片 */
    opacity: 1; /* 调整透明度（0.8表示80%的不透明度） */
    }
    
    .media-container {
        position: relative;
        width: 100px;
        height: 100px;
        overflow: hidden;
    }
    .media-container img, .media-container video {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: contain;
    }
    .media-container img.active, .media-container video.active {
        display: block;
    }
    .media-container button {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        background: rgba(255, 255, 255, 0.7);
    }
    .media-container .prev-button {
        left: 10px;
    }
    .media-container .next-button {
        right: 10px;
    }

    .centered-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .low-stock {
        font-weight: bold;
        color: red;
    }
</style>
