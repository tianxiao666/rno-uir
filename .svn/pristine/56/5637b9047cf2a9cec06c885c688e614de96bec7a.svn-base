import { Component, ViewChild, ElementRef } from '@angular/core';
import { DataSearchService } from './data-search.service';
import { SearchValue } from './condition/search-value';
import { TableSearchValue } from './table-search-value';
import { Parameter } from './data-table/get-parameter-value';
import { ExcelService } from '../common/export.service';

@Component({
  selector: 'xdr-data-search',
  templateUrl: './data-search.component.html',
  styleUrls: ['./data-search.component.scss'],
})

export class DataSearchComponent {
  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;
  public isShowLoading: boolean = false;
  public isShowDetailTable: boolean = false;
  private isShowEcharts: boolean = false;
  private rowsOnPage: any;
  private activePage: any;
  private date1: any;
  private date2: any;
  private area: any;
  private device: any;
  private assortment: any;
  private cellAreaInput: any;
  private cellNameInput: any;
  private showPRB: any;
  private data: any;
  private dataId: any;
  private dataName: any;
  private chartOption2: any;
  private prbAXisValue = this.setPRBxAxis();
  public areas: string[];

  constructor(private _analysisService: DataSearchService, private excelService: ExcelService) {
    this.excelService = excelService;
  }

  public OnAreaChg(ar: string[]){
    this.areas = ar;
  }

  public searchParameterData(e: Parameter) {
    if (this.rowsOnPage) {
      if (this.rowsOnPage !== e.rowsOnPage) {
        this.activePage = 0;
        this.rowsOnPage = e.rowsOnPage;
      }else {
        this.activePage = e.activePage;
      }
    }else {
      this.activePage = e.activePage;
      this.rowsOnPage = e.rowsOnPage;
    }
  }

  public search(e: SearchValue) {
    if (this.area) {
      if (this.device !== e.device || this.area !== e.area || this.date1 !== e.date1 || this.date2 !== e.date2 || this.assortment !== e.assortment || this.cellAreaInput !== e.cellAreaInput || this.cellNameInput !== e.cellNameInput) {
        this.activePage = 0;
      }
    }
    this.date1 = e.date1;
    this.date2 = e.date2;
    this.area = e.area;
    this.device = e.device;
    this.assortment = e.assortment;
    this.cellAreaInput = e.cellAreaInput;
    this.cellNameInput = e.cellNameInput;
    this.showPRB = e.showPRB;
    const final = new TableSearchValue(
      this.date1,
      this.date2,
      this.area,
      this.device,
      this.assortment,
      this.cellAreaInput,
      this.cellNameInput,
      this.showPRB,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    // alert(final.area);
    this.isShowLoading = true;
    this.getTableData(final);
  }

  public export(e: SearchValue) {
    const final = new TableSearchValue(
      e.date1,
      e.date2,
      e.area,
      e.device,
      e.assortment,
      e.cellAreaInput,
      e.cellNameInput,
      e.showPRB,
      this.rowsOnPage,
      this.activePage,
      'download',
    );
    // console.log(final);
    this.isShowLoading = true;
    this._analysisService.getData(final)
      .then(data => {
        this.isShowLoading = false;
        if (data.json.length === 0) {
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'block';
          }, 0);
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'none';
          }, 3000);
          return;
        }else {
          // console.log(data.json)
          if (final.showPRB === 'checked') {
            const id = [];
            for (let i=0; i<116; i++) {
              id.push(i);
            }
            this.dataId = id;
            const name = ['id', '干扰日期','地市', '设备', '干扰分类', '小区名称', '小区', '所属频段', 'ICM1', 'ICM2', 'ICM3', 'ICM4', 'ICM5', '最大值', '平均值', '干扰系数'];
            for (let j=0; j<100; j++) {
              name.push('PRB'+j);
            };
            this.dataName = name;
          }else {
            this.dataId = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15'];
            this.dataName = ['id', '干扰日期','地市', '设备', '干扰分类', '小区名称', '小区', '所属频段', 'ICM1', 'ICM2', 'ICM3', 'ICM4', 'ICM5', '最大值', '平均值', '干扰系数'];
          }
          let dataJ = '[';
          let num = 1;
          for (const i of data.json) {
            dataJ += '{';
            for (let j = 0; j < (this.dataId).length; j++) {
              dataJ += `"${this.dataName[j]}": "${i[this.dataId[j]]}"`;
              if (j < (this.dataId).length - 1) {
                dataJ += ',';
              }
            }
            dataJ += '}';
            if (num < data.json.length) {
              dataJ += ',';
              num++;
            }
          }
          dataJ += ']';
          this.excelService.exportAsExcelFile(JSON.parse(dataJ), '上行干扰数据');
        }
      }).catch(ex => {
      // console.log(e);
      this.isShowLoading = false;
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'none';
      }, 3000);
      return;
    });
  }

  public pageParameterData(e: SearchValue) {
    const tableSearch = new TableSearchValue(
      this.date1,
      this.date2,
      this.area,
      this.device,
      this.assortment,
      this.cellAreaInput,
      this.cellNameInput,
      this.showPRB,
      this.rowsOnPage,
      this.activePage,
      'query',
    );
    this.getTableData(tableSearch);
  }

  private getTableData(e: TableSearchValue) {
    this._analysisService.getData(e)
      .then(data => {
        this.isShowLoading = false;
        this.isShowEcharts = false;
        if (data.json.length === 0) {
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'block';
          }, 0);
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'none';
          }, 3000);
          this.isShowDetailTable = false;
          return;
        }else {
          const totalItems = data.headers.get('X-Total-Count');
          const dataFinal = [];
          dataFinal.push(data.json);
          dataFinal.push(totalItems);
          dataFinal.push(this.activePage);
          dataFinal.push(e.showPRB);
          this.data = dataFinal;
          this.isShowDetailTable = true;
        }
      }).catch(ex => {
      // console.log(e);
      this.isShowLoading = false;
      this.isShowEcharts = false;
      this.isShowDetailTable = false;
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'block';
      }, 0);
      setTimeout(() => {
        this.programError.nativeElement.style.display = 'none';
      }, 3000);
      return;
    });
  }

  private getChartData(e: string) {
    for (const icm of this.data[0]) {
      if (e === icm['0']) {
        this.isShowLoading = false;
        this.isShowDetailTable = false;
        const prbYXisValue = [];
        for (let i=16; i<116; i++) {
          prbYXisValue.push(icm[i]);
        }
        this.chartOption2 = {
          'color': ['#3398DB'],
          'tooltip': {
            'trigger': 'axis',
            'axisPointer': {      // 坐标轴指示器，坐标轴触发有效
              'type': 'shadow',    // 默认为直线，可选为：'line' | 'shadow'
            },
          },
          'grid': {
            'left': '3%',
            'right': '4%',
            'bottom': '3%',
            'containLabel': true,
            'y2': 140,
          },
          'xAxis': [
            {
              'type': 'category',
              'data': this.prbAXisValue,
              'axisTick': {
                'alignWithLabel': true,
              },
              'axisLabel': {
                'interval': 2,//横轴信息间隔
                'rotate': -30,//-30度角倾斜显示
              },
            },
          ],
          'yAxis': [
            {
              'type': 'value',
              'min': -150,
              'max': -70,
            },
          ],
          'series': [
            {
              'name': 'PRB值',
              'type': 'line',
              'barWidth': '60%',
              'data': prbYXisValue,
            },
          ],
        };
        this.isShowEcharts = true;
      }
    }
  }

  private setPRBxAxis() {
    const PRBValue = [];
    for (let i=0; i<100; i++) {
      PRBValue.push('prb'+i)
    }
    return PRBValue
  }

}
