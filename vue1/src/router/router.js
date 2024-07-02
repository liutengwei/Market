import { createRouter, createWebHashHistory } from 'vue-router';
import LoginComponent from '@/components/LoginComponent.vue';
import RegisterComponent from '@/components/RegisterComponent.vue';
import BuyerMain from '@/components/BuyerMain.vue';
import ShowOrderInfo from '@/components/ShowOrderInfo.vue';
import UpdatePassword from '@/components/UpdatePassword.vue';
import SellerMain from '@/components/SellerMain.vue';
import ForgotPassword from '@/components/ForgotPassword.vue';
import BuyerCart from '@/components/BuyerCart.vue';
import BuyerLikes from '@/components/BuyerLikes.vue';
import BuyerPay from '@/components/BuyerPay.vue';
import BuyerHistory from '@/components/BuyerHistory.vue';
import BuyershowRecommend from '@/components/BuyershowRecommend.vue';
import BuyershowCat from '@/components/BuyershowCat.vue';
import BuyerUploadCat from '@/components/BuyerUploadCat.vue';
import ShowAllUsers from '@/components/ShowAllUsers.vue';
import ShowGoods from '@/components/ShowGoods.vue';
import UploadOneGood from '@/components/UploadOneGood.vue';
import UserOrderHistory from '@/components/UserOrderHistory.vue';
import ShowHistoryGoods from '@/components/ShowHistoryGoods.vue';
import ShowSearchHistoryGoods from '@/components/ShowSearchHistoryGoods.vue';
import UploadMultipleGoods from '@/components/UploadMultipleGoods.vue';
import AfterSalesTreatment from '@/components/AfterSalesTreatment.vue';
import ManageLogistics from '@/components/ManageLogistics.vue';
const routes = [
    { path: '/', component: LoginComponent },
    { path: '/login', component: LoginComponent },
    { path: '/register', component: RegisterComponent },
    {
        path: '/buyer',
        component: BuyerMain,
        children: [
            { path: 'cart', name: 'BuyerCart', component: BuyerCart },
            { path: 'likes', name: 'BuyerLikes', component: BuyerLikes },
            { path: 'pay', name: 'BuyerPay', component: BuyerPay },
            { path: 'history', name: 'BuyerHistory', component: BuyerHistory },
            { path: 'show-recommend', name: 'BuyershowRecommend', component: BuyershowRecommend },
            { path: 'show-cat', name: 'BuyershowCat', component: BuyershowCat },
            { path: 'upload-cat', name: 'BuyerUploadCat', component: BuyerUploadCat }
        ]
    },
    {
        path: '/seller',
        component: SellerMain,
        children: [
            { path: 'update-password', name: 'UpdatePassword', component: UpdatePassword },
            { path: 'show-orderinfo', name: 'ShowOrderInfo', component: ShowOrderInfo },
            { path: 'show-allusers', name: 'ShowAllUsers', component: ShowAllUsers },
            { path: 'showgoods', name: 'ShowGoods', component: ShowGoods },
            { path: 'uploadonegood', name: 'UploadOneGood', component: UploadOneGood },
            { path: 'userorderhistory', name: 'UserOrderHistory', component: UserOrderHistory },
            { path: 'showhistorygoods', name: 'ShowHistoryGoods', component: ShowHistoryGoods },
            { path: 'showsearchhistorygoods', name: 'ShowSearchHistoryGoods', component: ShowSearchHistoryGoods },
            { path: 'UploadMultiplegoods', name: 'UploadMultipleGoods', component: UploadMultipleGoods },
            { path: 'aftersalestreatment', name: "AfterSalesTreatment", component: AfterSalesTreatment },
            { path: 'managelogistics', name: 'ManageLogistics', component: ManageLogistics },
        ]
    },
    { path: '/forgot-password', name: 'ForgotPassword', component: ForgotPassword },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

export default router;
