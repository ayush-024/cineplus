import { EventData, Page } from '@nativescript/core';
import { HomePageModel } from './home-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  page.bindingContext = new HomePageModel();
}

export function onMovieItemTap(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as HomePageModel;
  model.navigateToMovieDetail(args.object.bindingContext);
}

export function onTVShowItemTap(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as HomePageModel;
  model.navigateToTVShowDetail(args.object.bindingContext);
}

export function onPlayTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as HomePageModel;
  model.playFeaturedContent();
}

export function onAddToListTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as HomePageModel;
  model.addToList();
}