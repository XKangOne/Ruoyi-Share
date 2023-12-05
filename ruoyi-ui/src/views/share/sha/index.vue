<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发布人id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入发布人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否原创" prop="isOriginal">
        <el-input
          v-model="queryParams.isOriginal"
          placeholder="请输入是否原创"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="概要信息" prop="summary">
        <el-input
          v-model="queryParams.summary"
          placeholder="请输入概要信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下载数 " prop="buyCount">
        <el-input
          v-model="queryParams.buyCount"
          placeholder="请输入下载数 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否显示" prop="showFlag">
        <el-input
          v-model="queryParams.showFlag"
          placeholder="请输入是否显示"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['share:sha:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['share:sha:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['share:sha:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['share:sha:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="shaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="发布人id" align="center" prop="userId" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="是否原创" align="center" prop="isOriginal" >
        <template slot-scope="scope">
          <div v-if="scope.row.isOriginal" style="background-color: red;">原创</div>
          <div v-else style="background-color: aqua;">转载</div>
        </template>
      </el-table-column>
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="封面" align="center" prop="cover" >
        <template #default="scope">
            <div style="text-align: center;">
                <el-image style="width: 80px; height: 80px" :src="scope.row.cover"/>
            </div>
        </template>
      </el-table-column>
      <el-table-column label="概要信息" align="center" prop="summary" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="下载地址" align="center" prop="downloadUrl" />
      <el-table-column label="下载数 " align="center" prop="buyCount" />
      <el-table-column label="是否显示" align="center" prop="showFlag" >
        <template slot-scope="scope">
          <div v-if="scope.row.showFlag" style="background-color: rosybrown;">显示</div>
          <div v-else style="background-color: beige;">不显示</div>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="审核不通过原因" align="center" prop="reason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['share:sha:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['share:sha:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改分享对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发布人id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入发布人id" />
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="评论" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入评论" />
        </el-form-item>
        <el-form-item label="是否原创" prop="isOriginal">
          <el-radio-group v-model="form.isOriginal">
            <el-radio label="0">原创</el-radio>
            <el-radio label="1">不是原创</el-radio>
          </el-radio-group>
          <!-- <el-input v-model="form.isOriginal" placeholder="请输入是否原创 0:否 1:是" /> -->
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="封面" prop="cover">
          <el-upload
              v-model:file-list="fileList"
              list-type="picture-card"
              ref="upload"
              class="upload-demo"
              action="http://localhost:8080/share/sha/uploadCover"         
              :on-success="handleSuccess"
              :auto-upload="true"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              
            </el-upload>
        </el-form-item>
        <el-form-item label="概要信息" prop="summary">
          <el-input v-model="form.summary" placeholder="请输入概要信息" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="下载地址" prop="downloadUrl">
          <el-input v-model="form.downloadUrl" placeholder="请输入下载地址" />
        </el-form-item>
        <el-form-item label="下载数 " prop="buyCount">
          <el-input v-model="form.buyCount" placeholder="请输入下载数 " />
        </el-form-item>
        <el-form-item label="是否显示" prop="showFlag">
          <el-radio-group v-model="form.showFlag">
            <el-radio label="0">显示</el-radio>
            <el-radio label="1">不显示</el-radio>
          </el-radio-group>
          <!-- <el-input v-model="form.showFlag" placeholder="请输入是否显示 0:否 1:是" /> -->
        </el-form-item>
        <el-form-item label="审核不通过原因" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入审核不通过原因" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSha, getSha, delSha, addSha, updateSha } from "@/api/share/sha";

export default {
  name: "Sha",
  dicts: ["sys_normal_disable"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 分享表格数据
      shaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      imageUrl: '',
      fileList:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        title: null,
        isOriginal: null,
        author: null,
        cover: null,
        summary: null,
        price: null,
        downloadUrl: null,
        buyCount: null,
        showFlag: null,
        auditStatus: null,
        reason: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "发布人id不能为空", trigger: "blur" }
        ],
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        isOriginal: [
          { required: true, message: "是否原创 0:否 1:是不能为空", trigger: "blur" }
        ],
        author: [
          { required: true, message: "作者不能为空", trigger: "blur" }
        ],
        summary: [
          { required: true, message: "概要信息不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        downloadUrl: [
          { required: true, message: "下载地址不能为空", trigger: "blur" }
        ],
        buyCount: [
          { required: true, message: "下载数 不能为空", trigger: "blur" }
        ],
        showFlag: [
          { required: true, message: "是否显示 0:否 1:是不能为空", trigger: "blur" }
        ],
        auditStatus: [
          { required: true, message: "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询分享列表 */
    getList() {
      this.loading = true;
      listSha(this.queryParams).then(response => {
        this.shaList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.shaList.map((item)=>{
          if(item.cover.startsWith("/")){
            return item.cover = process.env.VUE_APP_BASE_API + item.cover;
          }
          return item.cover;
      })
      console.log(this.shaList);
      });
      
      
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        title: null,
        createTime: null,
        updateTime: null,
        remark: null,
        createBy: null,
        updateBy: null,
        isOriginal: null,
        author: null,
        cover: null,
        summary: null,
        price: null,
        downloadUrl: null,
        buyCount: null,
        showFlag: null,
        auditStatus: null,
        reason: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分享";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSha(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分享";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$refs.upload.submit()
          if (this.form.id != null) {
            updateSha(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSha(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除分享编号为"' + ids + '"的数据项？').then(function() {
        return delSha(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('share/sha/export', {
        ...this.queryParams
      }, `sha_${new Date().getTime()}.xlsx`)
    },
    handleSuccess(response) {
      console.log(response);
      this.form.cover = response.coverUrl;
      // this.submitForm();
      
    },
  }
};
</script>
