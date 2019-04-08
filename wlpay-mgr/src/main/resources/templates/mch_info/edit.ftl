<div style="margin: 15px;">
	<form class="layui-form">
		<#if (item.mchId!"") != "">
		<div class="layui-form-item">
			<label class="layui-form-label">商户ID</label>
			<div class="layui-input-block">
				<input type="text" name="mchId" placeholder="请输入商户ID" autocomplete="off" class="layui-input"  readonly="readonly" value="${item.mchId?if_exists }">
			</div>
		</div>
		</#if>
		<div class="layui-form-item">
			<label class="layui-form-label">商户名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" lay-verify="required" placeholder="请输入商户名称" autocomplete="off" class="layui-input" value="${item.name?if_exists }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">商户账号</label>
			<div class="layui-input-block">
				<input type="text" name="username" lay-verify="required" placeholder="请输入商户账号" autocomplete="off" class="layui-input" value="${item.username?if_exists }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">商户密码</label>
			<div class="layui-input-block">
				<input type="text" name="password" lay-verify="required" placeholder="请输入商户密码" autocomplete="off" class="layui-input" value="${item.password?if_exists }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">商户手机号</label>
			<div class="layui-input-block">
				<input type="text" name="phone" lay-verify="required" placeholder="请输入商户手机号" autocomplete="off" class="layui-input" value="${item.phone?if_exists }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">商户邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="email" lay-verify="required" placeholder="请输入商户邮箱"   autocomplete="off" class="layui-input" value="${item.email?if_exists }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">商户类型</label>
			<div class="layui-input-block">
				<select name="type" lay-verify="required">
					<option value=""></option>
                    <option value="1" <#if (item.type!"") == "1">selected="selected"</#if>>平台账户</option>
                    <option value="2" <#if (item.type!"") == "2">selected="selected"</#if>>私有账户</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否启用</label>
			<div class="layui-input-block">
				<input type="checkbox" name="state" lay-skin="switch" <#if (item.state!1) == 1>checked="checked"</#if> >
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">请求私钥</label>
			<div class="layui-input-block">
				<textarea name="reqKey" placeholder="请输入商户请求私钥" lay-verify="required" class="layui-textarea">${item.reqKey?if_exists }</textarea>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">响应私钥</label>
			<div class="layui-input-block">
				<textarea name="resKey" placeholder="请输入商户响应私钥" lay-verify="required" class="layui-textarea">${item.resKey?if_exists }</textarea>
			</div>
		</div>
		<button lay-filter="edit" lay-submit style="display: none;"></button>
	</form>
</div>