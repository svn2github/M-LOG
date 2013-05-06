<#include "../../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="baeForm" name="baeForm" method="post" action="${base}/admin/comment/spam/config/save">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">Akismet配置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">是否启用反垃圾:</td>
			<td>
				<input type="checkbox" <#if akismet_enable?exists && akismet_enable == "true">checked="checked"</#if> onclick='$("#akismet_enable").val(this.checked);' />
				<input type="hidden" id="akismet_enable" name="akismet_enable" value="${akismet_enable?default("false")}" />
			</td>
			<td class="fieldnotice" style="width:300px;"></td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">Akismet Key:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="akismet_key" value="${akismet_key?default("")}" validate="{maxlength:100, messages:{maxlength:'Akismet Key最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		//斑马线
		var tables=document.getElementsByTagName("table");
		var b=false;
		for (var j = 0; j < tables.length; j++){
			var cells = tables[j].getElementsByTagName("tr");
			//cells[0].className="color3";
			b=false;
			for (var i = 0; i < cells.length; i++){
				if(b){
					cells[i].className="color2";
					b=false;
				}
				else{
					cells[i].className="color3";
					b=true;
				};
			};
		}
		
		mlog.form.validate({
			selector : "#baeForm",
			errorLabelContainer : "#error",
			wrapper: 'li',
			onfocusout : false,
			onkeyup : false,
			onclick : false,
			success : function(){
				mlog.utils.scrollTop();
			}
		});
	});
</script>
<#include "../../inc/footer.ftl" />