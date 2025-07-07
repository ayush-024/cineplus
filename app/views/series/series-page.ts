import { EventData, Page } from '@nativescript/core';
import { SeriesPageModel } from './series-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  page.bindingContext = new SeriesPageModel();
}

export function onPopularTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as SeriesPageModel;
  model.loadSeries('popular');
}

export function onTopRatedTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as SeriesPageModel;
  model.loadSeries('top_rated');
}

export function onOnTheAirTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as SeriesPageModel;
  model.loadSeries('on_the_air');
}

export function onSeriesItemTap(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as SeriesPageModel;
  model.navigateToSeriesDetail(args.object.bindingContext);
}