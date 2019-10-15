<template>
    <Tabs value="name1">
        <TabPane label="部门设置" name="DepMana">标签一的内容</TabPane>
        <TabPane label="职位设置" name="PosMana">标签一的内容</TabPane>
        <TabPane label="职称设置" name="ECMana">标签一的内容</TabPane>
    </Tabs>
</template>
<script>
    import MenuRole from './basic/MenuRole.vue'
    export default {
    data() {
        return {
            
        };
    },
    methods: {},
    components: {
        'menu-role': MenuRole,
        'dep-mana': DepMana,
        'ec-mana': ECMana,
        'jobtitle-mana': JobTitleMana,
        'pos-mana': PosMana
    }
    };
</script>
