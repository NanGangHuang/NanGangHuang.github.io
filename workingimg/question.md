###### 手机app流程跑到流程附件中去
```jsp
<div class="weui_cell">
<div class="weui_cell_bd weui_cell_primary">
<div class="weui_uploader_bd" id="report">
<ul class="weui_uploader_files" id="reportAttachment"></ul>
<div class="weui_uploader_input_wrp" onclick="reportAttachmentProxy.openImage()"></div>
<div class="weui_uploader_input_edit" onclick="reportAttachmentProxy.touchEditMode()">
<bpm:nls>编辑</bpm:nls></div>
<input name="reportMediaIds" id="reportMediaIds" type="hidden">
</div>
</div>
</div>

<script  type="text/javascript">
var reportAttachmentProxy=new SkyImage();
reportAttachmentProxy.bind({type:"inspection",target: 'reportAttachment',value: 'reportMediaIds',uploadNow: true,addSignName:true });
</script>

```

**问题原因：**实测文件名设置不对：`target`和`value`,设置错误会被识别为流程附件

