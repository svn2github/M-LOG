<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div id="error" class="message error" style="display:none;"></div>
	<@spring.bind "linkType" />
	<form class="form" name="linkTypeForm" id="linkTypeForm" action="${base}/admin/linkType/edit/save" method="POST">
		<table class="formtable">
			<tr>
				<td class="fieldlabel" style="width:60px;">编号</td>
				<td>
					<@spring.formInput path="linkType.id" attributes='class="textinput" readonly="readonly" style="width:98%;"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">名称</td>
				<td>
					<@spring.formInput path="linkType.name" attributes='class="textinput" style="width:98%;" validate=\'{required:true, maxlength:10, messages:{required:"请输入分类名称", maxlength:"分类名字长度不能超过{0}"}}\'' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">是否可见</td>
				<td style="font-size:12px;">
					<@spring.formRadioButtons path="linkType.visable" options=visable defaultValue="true" separator="&nbsp;" />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">排序</td>
				<td>
					<@spring.formInput path="linkType.order" attributes='class="textinput" style="width:98%;" validate=\'{digits: true, range:[1,1000], messages:{digits:"排序号必须为数字", range:"排序号的范围必须在{0}-{1}之间"}}\'' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">描述</td>
				<td>
					<@spring.formTextarea path="linkType.description" attributes='class="textinput" style="width:98%;" validate=\'{maxlength:2000, messages:{maxlength:"分类描述长度不能超过{0}"}}\'' />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center;">
					<input type="submit" class="btn" value=" 提交 " />
				</td>
			</tr>
		</table>
	</form>

	<script type="text/javascript">
		turnHighLight(120030);
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
				selector : "#linkTypeForm",
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
<#include "../inc/footer.ftl" />