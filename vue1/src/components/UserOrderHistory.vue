<template>
    <div class="container">
        <h1 style="color:red">功能正在加快开发中------</h1>
    </div>
</template>


<script>
import axios from 'axios';
export default {
    data() {
        return {
            isLoggedIn: true,
            orders: [],
            currentPage: 1,
            itemsPerPage: 5,
            currentUser: null,

        }

    },
    mothods: {
        async fetchRecord() {
            try {
                const data = {
                    userid: this.currentUser.userid
                }
                const response = await axios.post('/order/userorderhistory-control', data, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                if(response.data.msg=="success"){
                    console.log("获取用户历史记录成功");
                    this.orders=response.data.data;
                }else{
                    console.error("获取用户历史数据失败");
                }
            } catch (error) {
                console.error("获取用户历史记录数据失败");


            }
        }
    },
    computed: {
        paginatedOrders() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            let end = null;
            if (this.currentPage === this.totalPages) {
                end = this.orders.length;
            } else {
                end = start + this.itemsPerPage;
            }
            return this.filteredOrders.slice(start, end);
        },
        totalPages() {
            return Math.ceil(this.orders.length / this.itemsPerPage);
        },
        isPrevDisabled() {
            return this.currentPage <= 1;
        },
        isNextDisabled() {
            return this.currentPage >= this.totalPages;
        },
        filteredOrders() {
            return this.orders.filter(order => order.orderstate !== -1);
        },
        paginatedItems() {
            const start = (this.currentPage - 1) * this.itemsPerPage;
            const end = this.currentPage * this.itemsPerPage;
            return this.orders.slice(start, end);
        },
    }











}
</script>



<style type="text/css" scoped>
body, html {
    height: 100%;
    margin: 0;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh; /* 使容器占满整个视窗高度 */
}

h1 {
    margin: 0;
}
.centered-container {
    text-align: center;
}

* {
    /* background-color: #FFF9F1; */

}

body {
    display: block;
}

.left {
    /* 买家导航 */
    width: 287px;
    height: 100vh;
    background-color: rgba(61, 61, 61, 0.33);
    position: relative;
    float: left;
    align-content: center;
}

.head1 {
    background-color: rgba(61, 61, 61, 0);
    position: relative;
    top: 30px;
    left: 70px;
}

.daohang {
    background-color: rgba(0, 0, 0, 0);
    width: 200px;
    /*格子宽度*/
    position: relative;
    left: 30px;
}

.head2 {
    background-color: rgba(61, 61, 61, 0.33);
    text-align: center;
    vertical-align: top;
    font-size: 36px;
    color: white;
    height: 100px;
    /*格子高度*/
}

.head4 {
    background-color: rgba(61, 61, 61, 0.33);
    text-align: center;
    height: 100px;
    /*格子高度*/
}

.head4-1 {
    background-color: rgba(61, 61, 61, 0);
    text-decoration: none;
    color: #ffffff;
    font-size: 28px;
    font-weight: bold;
}

.head5 {
    background-color: rgba(61, 61, 61, 0.33);
    text-align: center;
    height: 100px;
    /*格子高度*/
    bottom: 0;

}

.head5-1 {
    background-color: rgba(61, 61, 61, 0);
    text-decoration: none;
    color: #585655;
    font-size: 28px;
    font-weight: bold;
}

/* 商品 */
.right {
    /* 商品显示
    width: 1340px;*/
    height: 100vh;
    /* background-color: aquamarine; */
    position: absolute;
    /*绝对定位*/
    left: 350px;

    float: right;
}


.goods {
    display: flex;
    /*使用flex布局*/
    flex-wrap: wrap;
    /*允许元素换行*/
    justify-content: space-between;
    /*元素之间留有空隙*/
    border: 1px solid #000;
    /*添加边框*/
}

.show-1 {
    flex: 0 0 30%;
    /*每个元素占用30%的宽度，这样每行就可以放3个元素*/
    border: 1px solid #000;
    /*添加边框*/
    margin-bottom: 10px;
    /*添加底部边距*/
}

.picture {
    text-align: center;
    /*图片居中*/
}

.price {
    font-size: 20px;
    height: 20px;
}

.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
}

.prev,
.next {
    background-color: rgb(237, 196, 110);
}

form {
    display: flex;
    /* 让表单内的元素在同一行显示 */
    width: 600px;
    height: 45px;
}

input[type="text"] {
    flex-grow: 1;
    /* 让搜索框占据剩余的空间 */
}

input[name="keyword"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

#search_list {
    position: fixed;
    top: 110px;
    left: 350px;
    width: 400px;
    background-color: white;
}


#search_list div {
    border-bottom: 1px solid black;
}
</style>