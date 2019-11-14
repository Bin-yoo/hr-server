<template>
    <div>
        <Divider orientation="left" ><Icon type="ios-people" />&nbsp;&nbsp;员工统计</Divider>
        <Row>
            <Col span="2">
                <Row>
                    <Icon type="ios-folder-outline" size="60"/>
                </Row>
                <Row>
                    <h4>总档案数：{{basic.total}}</h4>
                </Row>
            </Col>
            <Col span="2">
                <Row>
                    <Icon type="ios-people-outline" size="60"/>
                </Row>
                <Row>
                    <h4>在职员工数：{{basic.inwork}}</h4>
                </Row>
            </Col>
            <Col span="2">
                <Row>
                    <Icon type="ios-exit-outline" size="60"/>
                </Row>
                <Row>
                    <h4>已离职职工数：{{basic.leave}}</h4>
                </Row>
            </Col>
        </Row>
        <br>
        <Row>
            <Col span="6">
                <div id="genderStatisticsPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="6">
                <div id="wedlockStatisticsPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="12">
                <div id="titopDegreeStatisticsBar" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
        <br>
        <br>
        <br>
        <Row>
            <Col span="12">
                <div id="RpStatisticsLine" style="width:100%; height:400px;"></div>                
            </Col>
            <Col span="12">
                <Tabs type="card" :animated="false">
                    <span slot="extra"><h2>职位结构</h2></span>
                    <TabPane label="职位">
                        <Table :columns="columns1" :data="posnumlist" height="300"></Table>
                    </TabPane>
                    <TabPane label="职称">
                        <Table :columns="columns1" :data="joblvlnumlist" height="300"></Table>
                    </TabPane>
                </Tabs>
            </Col>
        </Row>
        <br>
        <br>
        <Divider orientation="left" ><Icon type="ios-briefcase-outline" />&nbsp;&nbsp;部门统计</Divider>
        <Row>
            <Col span="12">
                <div id="deparStatisticsPie" style="width:100%; height:400px;"></div>
            </Col>
            <Col span="12">
                <div id="deparStatisticsBar" style="width:100%; height:400px;"></div>
            </Col>
        </Row>
    </div>
</template>

<script>
    import echarts from 'echarts'
    export default {
        data() {
            return {
                columns1: [
                    {
                        title: '名称',
                        key: 'name'
                    },
                    {
                        title: '人数',
                        key: 'value'
                    },
                    {
                        title: '占比',
                        key: 'proportion'
                    }
                ],
                basic:[],
                depnumlist:[],
                posnumlist:[],
                joblvlnumlist:[],
            }
        },
        watch:{
            basic: 'drawCharts',
            depnumlist: 'drawCharts',
            posnumlist: 'drawCharts',
            joblvlnumlist: 'drawCharts',
        },
        methods: {
            drawGenderChart() {
                this.genderStatisticsPie = echarts.init(document.getElementById('genderStatisticsPie'));
                this.genderStatisticsPie.setOption({
                    title: {
                        text: '性别结构',
                        x: 'center'
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['男性', '女性']
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: this.basic.male, name: '男性' },
                                { value: this.basic.female, name: '女性' },
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            },
                            label: {
                                normal: {
                                    position: 'inner',
                                    formatter: '  {b|{b}：}{c}人  {per|{d}%}  ',
                                    backgroundColor: '#eee',
                                    borderColor: '#aaa',
                                    borderWidth: 1,
                                    borderRadius: 4,
                                    rich: {
                                        b: {
                                            lineHeight: 25
                                        },
                                        per: {
                                            color: '#eee',
                                            backgroundColor: '#334455',
                                            padding: [2, 4],
                                            borderRadius: 2
                                        }
                                    }
                                }
                            }
                        }
                    ]
                });
                this.wedlockStatisticsPie = echarts.init(document.getElementById('wedlockStatisticsPie'));
                this.wedlockStatisticsPie.setOption({
                    title: {
                        text: '婚姻状况',
                        x: 'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{b} : {c} 人"
                    },
                    legend: {
                        orient: 'vertical',
                        bottom: 'bottom',
                        data: ['未婚', '已婚', '离异']
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                { value: this.basic.unmarried, name: '未婚' },
                                { value: this.basic.married, name: '已婚' },
                                { value: this.basic.divorced, name: '离异' },
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            },
                            label: {
                                normal: {
                                    formatter: '  {b|{b}：}{c}人  {per|{d}%}  ',
                                    backgroundColor: '#eee',
                                    borderColor: '#aaa',
                                    borderWidth: 1,
                                    borderRadius: 4,
                                    rich: {
                                        b: {
                                            lineHeight: 25
                                        },
                                        per: {
                                            color: '#eee',
                                            backgroundColor: '#334455',
                                            padding: [2, 4],
                                            borderRadius: 2
                                        }
                                    }
                                }
                            }
                        }
                    ]
                });
            },
            drawTitopDegreeChart() {
                this.titopDegreeStatisticsBar = echarts.init(document.getElementById('titopDegreeStatisticsBar'));
                this.titopDegreeStatisticsBar.setOption({
                    title: {
                        text: '学历结构',
                        x: 'center'
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        },
                        formatter: "{b} : {c} 人"
                    },
                    xAxis: {
                        type: 'value',
                    },
                    yAxis: {
                        type: 'category',
                        data: ['其他','小学','初中','高中','大专','本科','硕士','博士'],
                        
                    },
                    series: [
                        {
                            type: 'bar',
                            data: [this.basic.other, this.basic.Primary, this.basic.middle, this.basic.high, this.basic.training, this.basic.undergraduate, this.basic.master, this.basic.dr],
                        }
                    ]
                });
            },
            drawRpChart() {
                this.RpStatisticsLine = echarts.init(document.getElementById('RpStatisticsLine'));
                this.RpStatisticsLine.setOption({
                    title: {
                        text: '奖惩',
                        left: '60px'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['2019-08', '2019-09', '2019-10', '2019-11', '2019-12', '2020-01', '2020-02'],
                        axisTick: {
                            alignWithLabel: true
                        },
                        boundaryGap: false,
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    yAxis: {
                        type: 'value'
                    },
                    legend: {
                        data: ['奖励', '惩罚']
                    },
                    series: [
                        {
                            name:'奖励',
                            type:'line',
                            data:[0, 1, 2, 1, 0, 5, 1]
                        },
                        {
                            name:'惩罚',
                            type:'line',
                            data:[0, 0, 0, 1, 1, 0, 1]
                        }
                    ]
                });
            },
            drawDeparChart() {
                this.deparStatisticsPie = echarts.init(document.getElementById('deparStatisticsPie'));
                this.deparStatisticsPie.setOption({
                    title: {
                        text: '部门人数统计',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} 人 ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: this.depnumlist
                    },
                    series: [
                        {
                            name: '部门人数',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: this.depnumlist,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
                this.deparStatisticsBar = echarts.init(document.getElementById('deparStatisticsBar'));
                var depname = [];
                this.depnumlist.forEach(element => {
                    depname.push(element.name);
                });
                this.deparStatisticsBar.setOption({
                    title: {
                        text: '部门人数统计',
                        x: 'center'
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        },
                        formatter: "{a} <br/>{b} : {c} 人"
                    },
                    xAxis: {
                        type: 'value',
                    },
                    yAxis: {
                        type: 'category',
                        data: depname
                    },
                    series: [
                        {
                            name: '部门人数',
                            type: 'bar',
                            data: this.depnumlist,
                        }
                    ]
                });
            },
            getTotalStatistics(){
                this.getRequest("/statistics/totalfile").then(resp=> {
                    console.log(resp.data.data)
                    this.basic = resp.data.data.basic;
                    this.depnumlist = resp.data.data.depnumlist;
                    this.posnumlist = resp.data.data.posnumlist;
                    this.joblvlnumlist = resp.data.data.joblvlnumlist;
                })
            },
            drawCharts() {
                this.drawGenderChart();
                this.drawTitopDegreeChart();
                this.drawRpChart();
                this.drawDeparChart();
            },
        },
        mounted: function () {
            this.getTotalStatistics();
        },
    }
</script>