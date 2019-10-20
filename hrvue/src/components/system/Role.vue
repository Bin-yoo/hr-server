<template>
    <Tabs value="RoleMana">
        <TabPane label="权限管理" name="RoleMana">
            <menu-role></menu-role>
        </TabPane>
        <TabPane label="角色管理" name="CharacterMana">
            
        </TabPane>
    </Tabs>
</template>
<script>
    import MenuRole from './role/MenuRole.vue'
    import {isNotNullORBlank} from '../../utils/utils'
    export default{
        components: {
            'menu-role': MenuRole,
        },
        data(){
            return {
            }
        },
        mounted: function () {
        },
        methods: {
        }
    }
</script>
<style>

</style>
