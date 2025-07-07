import { EventData, Page } from '@nativescript/core';
import { DetailPageModel } from './detail-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  const context = page.navigationContext;
  page.bindingContext = new DetailPageModel(context.content, context.type);
}

export function onBackTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as DetailPageModel;
  model.navigateBack();
}

export function onPlayTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as DetailPageModel;
  model.playContent();
}

export function onAddToListTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as DetailPageModel;
  model.addToList();
}