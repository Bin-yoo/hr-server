<template>
  <div>
      <Row>
          <Col span="3"><h1>员工奖惩管理</h1></Col>
      </Row>
      <Row :style="{margin: '15px 0 0 0'}" type="flex" align="middle">
          <Col span="5"><Input suffix="ios-search" placeholder="请输入关键字"/>
          </Col>
          <Col span="1">
              <Button icon="ios-search">搜索</Button>
          </Col>
          <Col span="3">
              <Dropdown>
                  <a href="javascript:void(0)">
                      全部
                      <Icon type="ios-arrow-down"></Icon>
                  </a>
                  <DropdownMenu slot="list">
                      <DropdownItem>本月</DropdownItem>
                  </DropdownMenu>
                  <DropdownMenu slot="list">
                      <DropdownItem>本周</DropdownItem>
                  </DropdownMenu>
              </Dropdown>
          </Col>
          <Col span="1" offset="13">
              <Button type="primary">添加</Button>
          </Col>
      </Row>
      <Row :style="{margin: '20px 0 0 0'}">
          <Table border ref="selection" :columns="columns" :data="data1">
              <template slot-scope="{ row, index }" slot="action">
                  <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">编辑
                  </Button>
                  <Button type="error" size="small" @click="remove(index)">删除</Button>
              </template>
          </Table>
      </Row>
      <Row :style="{margin: '20px 0 0 0'}">
          <Col span="1">
              <Button type="error">批量删除</Button>
          </Col>
          <Page :total="100" show-elevator/>
      </Row>
  </div>
</template>
<script>
    export default{
        data() {
            return {
                columns: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '工号',
                        key: 'jobNum'
                    },
                    {
                        title: '部门',
                        key: 'department'
                    },
                    {
                        title: '职位',
                        key: 'position'
                    },
                    {
                        title: '日期',
                        key: 'date'
                    },
                    {
                        title: '奖惩类别',
                        key: 'category'
                    },
                    {
                        title: '奖惩原因',
                        key: 'reason'
                    },
                    {
                        title: '奖惩分数',
                        key: 'fraction'
                    },
                    {
                        title: '备注',
                        key: 'remarks'
                    },

                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        align: 'center'
                    }
                ],
                data1: [
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                    {
                        name: '宇哥',
                        jobNum: 20191016001,
                        department: "人事部",
                        position: "人事部经理",
                        date: "2019年10月16日",
                        category: "惩罚",
                        reason: "迟到",
                        fraction: "-2",
                        remarks: "无",
                    },
                ],
            }
        },
    }
</script>