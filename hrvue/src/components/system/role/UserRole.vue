<template>
    <Table :show-header="showheader" :columns="columns" :data="data1"></Table>
</template>
<script>
    export default{
        data(){
            return {
                columns:[
                    {
                        title: 'Name',
                        key: 'name'
                    },
                    {
                        title: 'Age',
                        key: 'age'
                    },
                    {
                        title: 'Address',
                        key: 'address'
                    }
                ],
                showheader: false,
                data1: [
                    {
                        name: 'John Brown',
                        age: 18,
                        address: 'New York No. 1 Lake Park',
                        date: '2016-10-03'
                    },
                    {
                        name: 'Jim Green',
                        age: 24,
                        address: 'London No. 1 Lake Park',
                        date: '2016-10-01'
                    },
                    {
                        name: 'Joe Black',
                        age: 30,
                        address: 'Sydney No. 1 Lake Park',
                        date: '2016-10-02'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    }
                ]
            }
        },
        mounted: function () {
            this.initRoles();
        },
        methods: {
            initRoles(){
                this.spinShow = true;
                this.getRequest("/system/role/roles").then(resp=> {
                    this.spinShow = false;
                    if (resp.status == 200 && resp.data.data != null) {
                        this.roles = resp.data.data;
                    }
                })
                //不延时的话会出BUG,打开任意折叠面板都会打开第一个
                // this.aitem = '-1';
                setTimeout(() => {
                    this.aitem = '-1';
                }, 100);
            },
            collapseChange(activeName){
                if (activeName == '') {
                    return;
                }
                var Roleid = this.roles[activeName].id;
                this.getRequest("/system/role/menuTree/" + Roleid).then(resp=> {
                    if (resp && resp.status == 200) {
                        this.treeData = resp.data.data;
                    }
                })
            },
            addNewRole(){
                var check = /\s/;
                if(!check.test(this.newRole.role) && !check.test(this.newRole.roleZh) && isNotNullORBlank(this.newRole.role,this.newRole.roleZh)){
                    this.spinShow = true;
                    this.postRequest("/system/role/role", {
                        name: this.newRole.role,
                        nameZh: this.newRole.roleZh
                    }).then(resp=> {
                        if (resp.data.error == false && resp.data.code == 200) {
                            this.initRoles();
                            this.newRole.role = '';
                            this.newRole.roleZh = '';
                            this.loading = false;
                            this.$Message.success(resp.data.data);
                        }
                    }).catch(error => {
                        this.spinShow = false;
                        this.$Message.error(error);
                    });
                } else {
                    this.$Message.error("角色中英文名称不能为空");
                }
            },
            updateTree(index){
                var treeCheckedData = this.$refs.tree[index].getCheckedNodes();
                var checkedKeys = [];
                treeCheckedData.forEach(item => {
                    if(item.children == null){
                        checkedKeys.push(item.id)
                    }
                });
                var Roleid = this.roles[index].id;
                this.putRequest("/system/role/updateMenuTree", {
                    rid: Roleid,
                    checkedKeys: checkedKeys
                }).then(resp=> {
                    this.$Message.success(resp.data.data);
                    this.spinShow = false;
                }).catch(error => {
                    this.spinShow = false;
                    this.$Message.error(error);
                });
            },
            cancel(){
                this.aitem = '-1';
            },
            deleteRole(index){
                var Roleid = this.roles[index].id;
                this.$Modal.confirm({
                    title: '你正在进行删除操作',
                    content: '<p>删除后相关账号的权限将更改为"普通员工"</p><p>你确定要删除该角色吗?</p>',
                    onOk: () => {
                        this.deleteRequest("/system/role/role/" + Roleid).then(resp=> {
                            this.$Message.success(resp.data.data);
                            this.spinShow = false;
                            this.initRoles();
                        }).catch(error => {
                            this.spinShow = false;
                            this.$Message.error(error);
                        });

                    },
                });
                
            }
        }
    }
</script>
