import { Observable, Frame } from '@nativescript/core';

export class MainPageModel extends Observable {
  private _currentPage: string = 'home';

  constructor() {
    super();
  }

  get currentPage(): string {
    return this._currentPage;
  }

  set currentPage(value: string) {
    if (this._currentPage !== value) {
      this._currentPage = value;
      this.notifyPropertyChange('currentPage', value);
    }
  }

  navigateToPage(page: string, moduleName: string) {
    this.currentPage = page;
    const frame = Frame.getFrameById('contentFrame');
    if (frame) {
      frame.navigate(moduleName);
    }
  }
}