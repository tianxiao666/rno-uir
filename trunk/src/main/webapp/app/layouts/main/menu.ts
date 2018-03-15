export const PAGES_MENU = [
    {
        path: 'main',
        children: [
            {
                path: 'dashboard',
                data: {
                    menu: {
                        title: '上行干扰数据导入',
                        icon: 'fa fa-cloud-upload',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu1']
                    },
                },
            },
            {
                path: 'test',
                data: {
                    menu: {
                        title: '上行干扰数据查询',
                        icon: 'fa fa-search',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu2']
                    },
                },
            },
            {
                path: 'perception-details',
                data: {
                    menu: {
                        title: '干扰样本数据训练',
                        icon: 'fa fa-train',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu3']
                    },
                },
            },
            {
                path: 'exception-cause',
                data: {
                    menu: {
                        title: '干扰样本数据测试',
                        icon: 'fa fa-bug',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu4']
                    },
                },
            },
            {
                path: 'exception-stats',
                data: {
                    menu: {
                        title: '干扰样本数据分类',
                        icon: 'fa fa-shopping-bag',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu5']
                    },
                },
            },
            {
                path: 'signaling-event-track',
                data: {
                    menu: {
                        title: '干扰数据统计分析',
                        icon: 'fa fa-calculator',
                        selected: false,
                        expanded: false,
                        order: 0,
                        authority: ['menu6']
                    },
                },
            },
            /*{
                path: '',
                data: {
                    menu: {
                        title: '管理',
                        icon: 'fa fa-user-plus',
                        selected: false,
                        expanded: false,
                        order: 0,
                    },
                },
                children: [
                    {
                        path: 'user-management',
                        data: {
                            menu: {
                                title: '用户管理',
                                icon: 'fa fa-fw fa-user'
                            }
                        }
                    },
                    {
                        path: 'jhi-metrics',
                        data: {
                            menu: {
                                title: '资源监控',
                                icon: 'fa fa-fw fa-tachometer'
                            }
                        }
                    },
                    {
                        path: 'jhi-health',
                        data: {
                            menu: {
                                title: '服务状态',
                                icon: 'fa fa-fw fa-heart'
                            }
                        }
                    },
                    {
                        path: 'jhi-configuration',
                        data: {
                            menu: {
                                title: '配置',
                                icon: 'fa fa-fw fa-list'
                            }
                        }
                    },
                    {
                        path: 'audits',
                        data: {
                            menu: {
                                title: '审核',
                                icon: 'fa fa-fw fa-bell'
                            }
                        }
                    },
                    {
                        path: 'logs',
                        data: {
                            menu: {
                                title: '日志',
                                icon: 'fa fa-fw fa-tasks'
                            }
                        }
                    },
                    {
                        path: 'docs',
                        data: {
                            menu: {
                                title: 'API',
                                icon: 'fa fa-fw fa-book'
                            }
                        }
                    },
                    {
                        path: './h2-console',
                        data: {
                            menu: {
                                title: '数据库',
                                icon: 'fa fa-fw fa-hdd-o'
                            }
                        }
                    },
                ]
            },
            {
                path: '',
                data: {
                    menu: {
                        title: '账号',
                        icon: 'fa fa-user',
                        selected: false,
                        expanded: false,
                        order: 0,
                    },
                },
                children: [
                    {
                        path: 'settings',
                        data: {
                            menu: {
                                title: '设置',
                                icon: 'fa fa-fw fa-wrench'
                            }
                        }
                    },
                    {
                        path: 'password',
                        data: {
                            menu: {
                                title: '密码',
                                icon: 'fa fa-fw fa-clock-o'
                            }
                        }
                    },
                    {
                        path: 'password',
                        data: {
                            menu: {
                                title: '密码',
                                icon: 'fa fa-fw fa-clock-o'
                            }
                        }
                    },
                ],
            }*/
        ],
    },
];
