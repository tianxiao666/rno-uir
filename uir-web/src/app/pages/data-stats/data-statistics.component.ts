import { Component, ViewChild, ElementRef } from '@angular/core';
import { DataStatisticsService } from './data-statistics.service';
import { SearchValue } from './condition/search-value';
import { CommonSearchValue } from './common-search-value';

@Component({
  selector: 'xdr-data-stats',
  templateUrl: './data-statistics.component.html',
  styleUrls: ['./data-statistics.component.scss'],
})

export class DataStatisticsComponent {
  @ViewChild('notFoundData') public notFoundDataDiv: ElementRef;
  @ViewChild('programError') public programError: ElementRef;

  public isShowLoading: boolean = false;
  public isShowDetailTable: boolean = false;
  private isShowEcharts: boolean = false;
  private isShowAnalysisEcharts: boolean = false;
  private data: any;
  private chartOption2: any;
  private chartOption3: any;
  private areas: any;

  constructor(private _analysisService: DataStatisticsService) {
  }

  public OnAreaCht(ar: string[]){
    this.areas = ar;
  }

  public statisticsSearch(e: SearchValue) {
    const searchPar = new CommonSearchValue(
      e.date1,
      e.date2,
      e.area,
      e.device,
      e.inputValue,
      e.showType,
    );
    this.isShowLoading = true;
    if (e.selectValue === 'analysis') {
      // console.info(e.inputValue);
      this.getAnalysisChart(searchPar);
    }else {
        this.getTableData(searchPar);
    }
  }

  private getTableData(e: CommonSearchValue) {
    this._analysisService.getData(e)
      .then(data => {
        this.isShowLoading = false;
        this.isShowAnalysisEcharts = false;
        if(e.showType=='table'){
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
            this.data = dataFinal;
            this.isShowDetailTable = true;
          }
        }else {
          this.isShowDetailTable = false;
          if (data.json.length === 0) {
            setTimeout(() => {
              this.notFoundDataDiv.nativeElement.style.display = 'block';
            }, 0);
            setTimeout(() => {
              this.notFoundDataDiv.nativeElement.style.display = 'none';
            }, 3000);
            this.isShowEcharts = false;
            return;
          } else {
            const yData = [];
            const xData = [];
            for (const icm of data.json) {
              xData.push(icm['0']);
              yData.push(icm['1']);
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
              },
              'xAxis': [
                {
                  'type': 'category',
                  'data': xData,
                  'axisTick': {
                    'alignWithLabel': true,
                  },
                },
              ],
              'yAxis': [
                {
                  'type': 'value',
                },
              ],
              'series': [
                {
                  'name': '小区数',
                  'type': 'bar',
                  'barWidth': '60%',
                  'data': yData,
                },
              ],
            };
            this.isShowEcharts = true;
          }
        }
      }).catch(ex => {
      // console.log(e);
      this.isShowEcharts = false;
      this.isShowAnalysisEcharts = false;
      this.isShowLoading = false;
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

  private getAnalysisChart(e: CommonSearchValue) {
    this._analysisService.getAnalysisData(e)
      .then(data => {
        this.isShowLoading = false;
        this.isShowEcharts = false;
        this.isShowDetailTable = false;
        if (data.json.length === 0) {
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'block';
          }, 0);
          setTimeout(() => {
            this.notFoundDataDiv.nativeElement.style.display = 'none';
          }, 3000);
          this.isShowAnalysisEcharts = false;
          return;
        }else {
          const avgData = [];
          const maxData = [];
          for (const icm of data.json) {
            for(let i=0;i<100;i++){
              avgData.push(icm[i]);
            }
            for(let j=100;j<200;j++){
              maxData.push(icm[j]);
            }
          }
          const prb = [];
          const textTitleValue = e.inputValue;
          for (let i = 0; i < 100; i++) {
            prb.push('prb'+i);
          }
          let textTitle = 'PRB值曲线';
          // console.info(e.inputValue);
          textTitle += textTitleValue;
          this.chartOption3 = {
            'title': {
              'text': textTitle,
              'textStyle': {
                'fontSize': 20,
                'fontWeight': 'bold',
              },
              'top': 10,
              'left': '18%',
            },
            'tooltip': {
              'trigger': 'axis',
            },
            'legend': {
              'data': [
                '最大值',
                '平均值',
              ],
              'top': 10,
              'right': '30%',
            },
            'xAxis': [
              {
                'type': 'category',
                'data': prb,
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
                'name': '最大值',
                'type': 'bar',
                'data': maxData,
                'itemStyle': {
                  'normal': {
                    'color': ['#3398DB'],
                  },
                },
              },
              {
                'name': '平均值',
                'type': 'line',
                'data': avgData,
              },
            ],
          };
        this.isShowAnalysisEcharts = true;
        }
      }).catch(ex => {
      // console.log(e);
      this.isShowEcharts = false;
      this.isShowAnalysisEcharts = false;
      this.isShowLoading = false;
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

}
