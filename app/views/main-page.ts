import { EventData, Page, Frame } from '@nativescript/core';
import { MainPageModel } from './main-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  page.bindingContext = new MainPageModel();
}

export function onHomeTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MainPageModel;
  model.navigateToPage('home', 'views/home/home-page');
}

export function onMoviesTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MainPageModel;
  model.navigateToPage('movies', 'views/movies/movies-page');
}

export function onSeriesTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MainPageModel;
  model.navigateToPage('series', 'views/series/series-page');
}

export function onSearchTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as MainPageModel;
  model.navigateToPage('search', 'views/search/search-page');
}