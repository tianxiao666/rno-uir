import { Object } from '../common/Object';

export class TableSearchValue {
  constructor(public date1: Object,
              public date2: Object,
              public area: string,
              public device: string,
              public assortment: string,
              public cellAreaInput: string,
              public cellNameInput: string,
              public showPRB: string,
              public rowsOnPage: number,
              public activePage: number,
              public operate: string,
  ) {
  }
}
