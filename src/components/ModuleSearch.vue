<template>
<div>
    <input type="text" class="form-control form-control-lg" placeholder="모듈번호 또는 모듈명 입력" v-model="keyword" @input="keyword = $event.target.value">

    <ul class="list-group data-list">
        <li class="list-group-item text-start" v-for="(module, index) in filterList" :key="index"
                    @click.self="loadModuleDetail(index)">
            <span class="badge bg-dark me-2">{{module.ncsClCd}}</span>
            {{module.compUnitName}}
            <span class="arrow" :class="{down:module.detailVisible}"></span>
            
            <ul class="list-group mt-2" v-if="module.detailVisible">
                <li class="list-group-item detail-list d-flex" v-for="(detail, subIndex) in module.detail.data" :key="subIndex">
                    <div class="col-sm-2"><span class="badge me-1" :class="getBadgeClass(detail.gbnName)">{{detail.gbnName}}</span></div>
                    <div class="col-sm-10 d-grid gap-1">
                        <span class="sub-title" @click="copyToClipboard(detail.compUnitFactrName)"><b>{{detail.compUnitFactrName}}</b></span>
                        <span class="copy-value" @click="copyToClipboard(detail.gbnVal)">{{detail.compUnitFactrNo}}-{{detail.gbnVal}}</span>
                    </div>
                </li>
            </ul>
        </li>
    </ul>
</div>
</template>
<script>
import Hangul from "hangul-js";
import { useToast } from "vue-toastification";

const toast = useToast();

export default {
    name:"ModuleSearch",
    data(){
        return {
            keyword:"",
            moduleList:[],
            filterList:[],
        };
    },
    methods:{
        async loadModuleList(){
            const resp = await this.$http.get(this.$host + "/");
            this.moduleList = resp.data;
        },
        async loadModuleDetail(index){
            const module = this.filterList[index];
            if(module.detailVisible === undefined) 
                module.detailVisible = false;

            if(module.detail) {
                module.detailVisible = !module.detailVisible;
            }
            else {
                const resp = await this.$http.get(this.$host + "/criteria/"+module.dutyCd+"/"+module.compUnitCd);
                this.filterList[index].detail = resp.data;
                if(this.filterList[index].detail.data.length > 1){
                    this.filterList[index].detail.data.sort((a, b)=>{
                        if(a.bgnCd != b.gbnCd){
                            return a.gbnCd.localeCompare(b.gbnCd);
                        }
                        return a.gbnVal.localeCompare(b.gbnVal);
                    });
                }
                module.detailVisible = true;
            }
        },
        searchModule(){
            if(!this.keyword) {
                this.filterList = [];
                return;
            }

            this.filterList = this.moduleList.filter(this.searchOperation);
        },
        searchOperation(m){
            if(/[\d_v]+/ig.test(this.keyword)){
                return (
                    Hangul.search(m.ncsClCd, this.keyword) >= 0
                )
            }
            else {
                const keyword = this.keyword.toLowerCase().replace(" ", "");
                return (
                    Hangul.search(m.compUnitName.toLowerCase().replace(" ", ""), keyword) >= 0
                );
            }
        },
        getModuleCode(module){
            return module.ncsLclasCd + module.ncsMclasCd + module.ncsSclasCd + module.ncsSubdCd;
        },
        copyToClipboard(value){
            this.$copyText(value);
            toast.success("복사 완료");
        },
        getBadgeClass(value){
            switch(value){
                case "수행준거": return "bg-primary";
                case "지식": return "bg-info";
                case "기술": return "bg-success";
                case "태도": return "bg-secondary";
            }
            return "";
        }
    },
    watch:{
        keyword(){
            this.searchModule();
        },
    },
    created(){
        this.loadModuleList();
    },
};
</script>
<style scoped>
    .list-group-item {
        cursor: pointer;
    }
    .arrow {
        margin: 1em;
    }

    .arrow::before {
        position: absolute;
        right:0;
        top:0;
        transform:translateY(75%);
        content: '';
        width: 0;
        height: 0;
        border: .5em solid transparent;
        border-left-color: gray;
        transform-origin: -15% 85%;
        transition: transform .25s;
    }

    .arrow.down::before {
        transform: rotate(90deg);
        transition: transform .15s;
    }

    .data-list {
        max-height: 75vh;
        overflow-y: auto;
    }
    .data-list::-webkit-scrollbar {
        width:1px;
    }
    .detail-list .copy-value:hover,
    .detail-list .sub-title:hover {
        color:var(--bs-danger);
    }
</style>